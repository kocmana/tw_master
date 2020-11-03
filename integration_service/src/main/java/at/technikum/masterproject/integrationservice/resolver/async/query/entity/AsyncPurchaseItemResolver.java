package at.technikum.masterproject.integrationservice.resolver.async.query.entity;

import at.technikum.masterproject.integrationservice.client.productservice.ProductInformationClient;
import at.technikum.masterproject.integrationservice.model.ecommerce.PurchaseItem;
import at.technikum.masterproject.integrationservice.model.product.Product;
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
public class AsyncPurchaseItemResolver implements GraphQLResolver<PurchaseItem> {

  private final ProductInformationClient productInformationClient;
  private final ResolverExecutor resolverExecutor;

  public CompletableFuture<Product> getProduct(PurchaseItem purchaseItem) {
    log.info("Retrieving product for purchaseItem {}, resolving asynchronously", purchaseItem.getId());
    return resolverExecutor.resolve(() -> productInformationClient.getProductById(purchaseItem.getProductId()));
  }
}
