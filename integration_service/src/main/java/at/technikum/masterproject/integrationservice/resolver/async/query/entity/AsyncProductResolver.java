package at.technikum.masterproject.integrationservice.resolver.async.query.entity;

import at.technikum.masterproject.integrationservice.client.ecommerceservice.PriceClient;
import at.technikum.masterproject.integrationservice.client.productservice.ProductReviewClient;
import at.technikum.masterproject.integrationservice.model.ecommerce.Price;
import at.technikum.masterproject.integrationservice.model.product.Product;
import at.technikum.masterproject.integrationservice.model.product.ProductReview;
import graphql.kickstart.tools.GraphQLResolver;
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
public class AsyncProductResolver implements GraphQLResolver<Product> {

  private final ProductReviewClient productReviewClient;
  private final PriceClient priceClient;

  public CompletableFuture<List<ProductReview>> getReviews(Product product) {
    log.info("Retrieving all product reviews for product {} query", product.getName());
    return productReviewClient.getAllProductReviewsForProduct(product.getId())
        .toFuture();
  }

  public CompletableFuture<Price> getPrice(Product product) {
    log.info("Retrieving current price for product {} query", product.getName());
    return priceClient.getCurrentPriceForProduct(product.getId())
        .toFuture();
  }

  public CompletableFuture<List<Price>> getPrices(Product product) {
    log.info("Retrieving all prices for product {} query", product.getName());
    return priceClient.getAllPricesForProduct(product.getId())
        .toFuture();
  }

}
