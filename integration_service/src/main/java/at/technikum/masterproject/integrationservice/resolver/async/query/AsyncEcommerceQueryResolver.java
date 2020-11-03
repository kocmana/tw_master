package at.technikum.masterproject.integrationservice.resolver.async.query;

import at.technikum.masterproject.integrationservice.client.ecommerceservice.PurchaseClient;
import at.technikum.masterproject.integrationservice.model.ecommerce.Purchase;
import at.technikum.masterproject.integrationservice.resolver.async.ResolverExecutor;
import graphql.kickstart.tools.GraphQLQueryResolver;
import java.util.concurrent.CompletableFuture;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(prefix = "services", name = "resolver-mode", havingValue = "ASYNC")
@Slf4j
@RequiredArgsConstructor
public class AsyncEcommerceQueryResolver implements GraphQLQueryResolver {

  private final PurchaseClient purchaseClient;
  private final ResolverExecutor resolverExecutor;

  public CompletableFuture<Purchase> purchase(Integer purchaseId) {
    log.info("Retrieved purchase query for purchaseId {}, resolving asynchronously", purchaseId);
    return resolverExecutor.resolve(() -> purchaseClient.getPurchase(purchaseId));
  }

}
