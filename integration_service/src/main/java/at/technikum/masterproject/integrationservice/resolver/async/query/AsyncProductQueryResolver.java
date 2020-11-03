package at.technikum.masterproject.integrationservice.resolver.async.query;

import at.technikum.masterproject.integrationservice.client.productservice.ProductInformationClient;
import at.technikum.masterproject.integrationservice.client.productservice.ProductReviewClient;
import at.technikum.masterproject.integrationservice.model.product.Product;
import at.technikum.masterproject.integrationservice.model.product.ProductReview;
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
public class AsyncProductQueryResolver implements GraphQLQueryResolver {

  private final ProductInformationClient productInformationClient;
  private final ProductReviewClient productReviewClient;
  private final ResolverExecutor resolverExecutor;

  public CompletableFuture<List<Product>> products() {
    log.info("Retrieving all products query, resolving asynchronously");
    return resolverExecutor.resolve(productInformationClient::getAllProducts);
  }

  public CompletableFuture<Product> product(int id) {
    log.info("Retrieved product query for id {}, resolving asynchronously", id);
    return resolverExecutor.resolve(() -> productInformationClient.getProductById(id));
  }

  public CompletableFuture<List<ProductReview>> reviews() {
    log.info("Retrieving all product reviews query, resolving asynchronously");
    return resolverExecutor.resolve(productReviewClient::getAllProductReviews);
  }

}
