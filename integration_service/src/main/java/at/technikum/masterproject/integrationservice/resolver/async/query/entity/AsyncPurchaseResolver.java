package at.technikum.masterproject.integrationservice.resolver.async.query.entity;

import at.technikum.masterproject.integrationservice.client.customerservice.CustomerInformationClient;
import at.technikum.masterproject.integrationservice.model.customer.Customer;
import at.technikum.masterproject.integrationservice.model.ecommerce.Purchase;
import at.technikum.masterproject.integrationservice.resolver.async.ResolverExecutor;
import graphql.kickstart.tools.GraphQLResolver;
import java.util.concurrent.CompletableFuture;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(prefix = "services", name = "resolver-mode", havingValue = "ASYNC")
@Slf4j
@RequiredArgsConstructor
public class AsyncPurchaseResolver implements GraphQLResolver<Purchase> {

  private final CustomerInformationClient customerInformationClient;
  private final ResolverExecutor resolverExecutor;

  public CompletableFuture<Customer> getCustomer(Purchase purchase) {
    log.info("Retrieving customer for purchase {}, resolving asynchronously", purchase.getId());
    return resolverExecutor.resolve(() -> customerInformationClient.getCustomerById(purchase.getCustomerId()));
  }
}
