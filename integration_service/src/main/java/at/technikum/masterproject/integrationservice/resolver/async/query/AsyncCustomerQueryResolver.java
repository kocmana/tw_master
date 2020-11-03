package at.technikum.masterproject.integrationservice.resolver.async.query;

import at.technikum.masterproject.integrationservice.client.customerservice.CustomerInformationClient;
import at.technikum.masterproject.integrationservice.client.customerservice.CustomerNetworkClient;
import at.technikum.masterproject.integrationservice.model.customer.Customer;
import at.technikum.masterproject.integrationservice.model.customer.CustomerNetwork;
import at.technikum.masterproject.integrationservice.resolver.async.ResolverExecutor;
import graphql.kickstart.tools.GraphQLQueryResolver;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(prefix = "services", name = "resolver-mode", havingValue = "ASYNC")
@Slf4j
@RequiredArgsConstructor
public class AsyncCustomerQueryResolver implements GraphQLQueryResolver {

  private final CustomerInformationClient customerInformationClient;
  private final CustomerNetworkClient customerNetworkClient;
  private final ResolverExecutor resolverExecutor;

  public CompletableFuture<List<Customer>> customers() {
    log.info("Retrieved get all customers query, resolving asynchronously");
    return resolverExecutor.resolve(customerInformationClient::getAllCustomer);
  }

  public CompletableFuture<Customer> customer(int customerId) {
    log.info("Retrieved customer query for customerId {}, resolving asynchronously", customerId);
    return resolverExecutor.resolve(() -> customerInformationClient.getCustomerById(customerId));
  }

  public CompletableFuture<List<CustomerNetwork>> customerNetwork(int customerId) {
    log.info("Retrieved customer network query for customerId {}, resolving asynchronously", customerId);
    return resolverExecutor.resolve(() -> customerNetworkClient.getNetworkByCustomerId(customerId));
  }

}
