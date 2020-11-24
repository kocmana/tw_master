package at.technikum.masterproject.integrationservice.resolver.dataloader.configuration;

import static at.technikum.masterproject.integrationservice.resolver.dataloader.configuration.DataloaderConstants.CUSTOMER_INFORMATION_DATALOADER;
import static at.technikum.masterproject.integrationservice.resolver.dataloader.configuration.DataloaderConstants.CUSTOMER_NETWORK_DATALOADER;
import static java.util.stream.Collectors.toConcurrentMap;

import at.technikum.masterproject.integrationservice.client.customerservice.CustomerInformationClient;
import at.technikum.masterproject.integrationservice.client.customerservice.CustomerNetworkClient;
import at.technikum.masterproject.integrationservice.model.customer.Customer;
import at.technikum.masterproject.integrationservice.model.customer.CustomerNetwork;
import java.util.AbstractMap.SimpleImmutableEntry;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.function.Function;
import lombok.RequiredArgsConstructor;
import org.dataloader.DataLoader;
import org.dataloader.DataLoaderRegistry;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnProperty(prefix = "services", name = "resolver-mode", havingValue = "DATALOADER")
@RequiredArgsConstructor
public class CustomerDataLoaderConfiguration {

  private final ExecutorService executorService;

  private final CustomerInformationClient customerInformationClient;
  private final CustomerNetworkClient customerNetworkClient;

  @Bean("customerServiceDataLoader")
  public DataLoaderRegistry create() {
    DataLoaderRegistry registry = new DataLoaderRegistry();
    registry.register(CUSTOMER_INFORMATION_DATALOADER, createCustomerInformationDataLoader());
    registry.register(CUSTOMER_NETWORK_DATALOADER, createCustomerNetworkDataLoader());
    return registry;
  }

  private DataLoader<Integer, Customer> createCustomerInformationDataLoader() {
    return DataLoader.newMappedDataLoader((Set<Integer> customerIds) ->
        CompletableFuture.supplyAsync(() -> customerIds.parallelStream()
                .map(customerInformationClient::getCustomerById)
                .collect(toConcurrentMap(Customer::getCustomerId, Function.identity()))
            , executorService));
  }

  private DataLoader<Integer, List<CustomerNetwork>> createCustomerNetworkDataLoader() {
    return DataLoader.newMappedDataLoader((Set<Integer> customerIds) ->
        CompletableFuture.supplyAsync(() -> customerIds.parallelStream()
                .map(customerId ->
                    new SimpleImmutableEntry<>(customerId, customerNetworkClient.getNetworkByCustomerId(customerId)))
                .collect(toConcurrentMap(SimpleImmutableEntry::getKey, SimpleImmutableEntry::getValue))
            , executorService));
  }
}
