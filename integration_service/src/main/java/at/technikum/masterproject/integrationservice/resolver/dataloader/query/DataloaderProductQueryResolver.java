package at.technikum.masterproject.integrationservice.resolver.dataloader.query;

import static at.technikum.masterproject.integrationservice.resolver.dataloader.configuration.DataloaderConstants.PRODUCT_INFORMATION_DATALOADER;

import at.technikum.masterproject.integrationservice.client.productservice.ProductInformationClient;
import at.technikum.masterproject.integrationservice.client.productservice.ProductReviewClient;
import at.technikum.masterproject.integrationservice.model.product.Product;
import at.technikum.masterproject.integrationservice.model.product.ProductReview;
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
public class DataloaderProductQueryResolver implements GraphQLQueryResolver {

  private final ProductInformationClient productInformationClient;
  private final ProductReviewClient productReviewClient;
  private final ResolverExecutor resolverExecutor;

  public CompletableFuture<List<Product>> products() {
    log.debug("Retrieving all products query, resolving asynchronously");
    return resolverExecutor.resolve(productInformationClient::getAllProducts);
  }

  public CompletableFuture<Product> product(int id, DataFetchingEnvironment environment) {
    log.debug("Retrieved product query for id {}, resolving with dataloader", id);
    DataLoader<Integer, Product> dataloader = environment.getDataLoader(PRODUCT_INFORMATION_DATALOADER);
    return dataloader.load(id);
  }

  public CompletableFuture<List<ProductReview>> reviews() {
    log.debug("Retrieving all product reviews query, resolving asynchronously");
    return resolverExecutor.resolve(productReviewClient::getAllProductReviews);
  }

}
