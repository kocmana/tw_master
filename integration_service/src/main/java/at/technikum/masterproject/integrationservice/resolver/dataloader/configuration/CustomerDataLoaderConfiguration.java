package at.technikum.masterproject.integrationservice.resolver.dataloader.configuration;

import static at.technikum.masterproject.integrationservice.resolver.dataloader.configuration.DataloaderConstants.CUSTOMER_INFORMATION_DATALOADER;
import static at.technikum.masterproject.integrationservice.resolver.dataloader.configuration.DataloaderConstants.CUSTOMER_NETWORK_DATALOADER;

import at.technikum.masterproject.integrationservice.client.customerservice.CustomerInformationClient;
import at.technikum.masterproject.integrationservice.client.customerservice.CustomerNetworkClient;
import at.technikum.masterproject.integrationservice.model.customer.Customer;
import at.technikum.masterproject.integrationservice.model.customer.CustomerNetwork;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.dataloader.DataLoader;
import org.dataloader.DataLoaderRegistry;
import org.dataloader.MappedBatchLoader;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnProperty(prefix = "services", name = "resolver-mode", havingValue = "DATALOADER")
@RequiredArgsConstructor
public class CustomerDataLoaderConfiguration {

  private final DataLoaderExecutor dataLoaderExecutor;
  private final CustomerInformationClient customerInformationClient;
  private final CustomerNetworkClient customerNetworkClient;

  @Bean("customerServiceDataLoaderRegistry")
  public DataLoaderRegistry create() {
    DataLoaderRegistry registry = new DataLoaderRegistry();
    registry.register(CUSTOMER_INFORMATION_DATALOADER, createCustomerInformationDataLoader());
    registry.register(CUSTOMER_NETWORK_DATALOADER, createCustomerNetworkDataLoader());
    return registry;
  }

  private DataLoader<Integer, Customer> createCustomerInformationDataLoader() {
    MappedBatchLoader<Integer, Customer> batchLoadFunction = dataLoaderExecutor.generateBatchLoadFunction(
        customerInformationClient::getCustomerById, Customer::getCustomerId);
    return DataLoader.newMappedDataLoader(batchLoadFunction);
  }

  private DataLoader<Integer, List<CustomerNetwork>> createCustomerNetworkDataLoader() {
    MappedBatchLoader<Integer, List<CustomerNetwork>> batchLoadFunction = dataLoaderExecutor.generateBatchLoadFunction(
        customerNetworkClient::getNetworkByCustomerId);
    return DataLoader.newMappedDataLoader(batchLoadFunction);
  }
}
