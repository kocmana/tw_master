package at.technikum.masterproject.integrationservice.resolver.dataloader.query;

import static at.technikum.masterproject.integrationservice.resolver.dataloader.configuration.DataloaderConstants.CUSTOMER_INFORMATION_DATALOADER;
import static at.technikum.masterproject.integrationservice.resolver.dataloader.configuration.DataloaderConstants.CUSTOMER_NETWORK_DATALOADER;

import at.technikum.masterproject.integrationservice.client.customerservice.CustomerInformationClient;
import at.technikum.masterproject.integrationservice.model.customer.Customer;
import at.technikum.masterproject.integrationservice.model.customer.CustomerNetwork;
import at.technikum.masterproject.integrationservice.resolver.async.ResolverExecutor;
import graphql.kickstart.tools.GraphQLQueryResolver;
import graphql.schema.DataFetchingEnvironment;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dataloader.DataLoader;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(prefix = "services", name = "resolver-mode", havingValue = "DATALOADER")
@Slf4j
@RequiredArgsConstructor
public class DataloaderCustomerQueryResolver implements GraphQLQueryResolver {

  private final CustomerInformationClient customerInformationClient;
  private final ResolverExecutor resolverExecutor;

  public CompletableFuture<List<Customer>> customers() {
    log.info("Retrieved get all customers query, resolving asynchronously");
    return resolverExecutor.resolve(customerInformationClient::getAllCustomer);
  }

  public CompletableFuture<Customer> customer(int customerId, DataFetchingEnvironment environment) {
    log.info("Retrieved customer query for customerId {}, resolving with dataloader", customerId);
    DataLoader<Integer, Customer> dataloader = environment.getDataLoader(CUSTOMER_INFORMATION_DATALOADER);
    return dataloader.load(customerId);
  }

  public CompletableFuture<List<CustomerNetwork>> customerNetwork(int customerId, DataFetchingEnvironment environment) {
    log.info("Retrieved customer network query for customerId {}, resolving with dataloader", customerId);
    DataLoader<Integer, List<CustomerNetwork>> dataloader = environment.getDataLoader(CUSTOMER_NETWORK_DATALOADER);
    return dataloader.load(customerId);
  }

}
