package at.technikum.masterproject.integrationservice.resolver;

import at.technikum.masterproject.integrationservice.client.ecommerceservice.PriceClient;
import at.technikum.masterproject.integrationservice.client.productservice.ProductReviewClient;
import at.technikum.masterproject.integrationservice.model.ecommerce.Price;
import at.technikum.masterproject.integrationservice.model.product.Product;
import at.technikum.masterproject.integrationservice.model.product.ProductReview;
import graphql.kickstart.tools.GraphQLResolver;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
@Slf4j
public class ProductResolver implements GraphQLResolver<Product> {

  private final ProductReviewClient productReviewClient;
  private final PriceClient priceClient;

  @Autowired
  public ProductResolver(
      ProductReviewClient productReviewClient,
      PriceClient priceClient) {
    this.productReviewClient = productReviewClient;
    this.priceClient = priceClient;
  }

  public List<ProductReview> getReviews(Product product) {
    log.info("Retrieving all product reviews for product {} query", product.getName());
    Flux<ProductReview> productReviews = productReviewClient.getAllProductReviewsForProduct(product.getId());
    return productReviews.collectList().block();
  }

  public Price getPrice(Product product) {
    log.info("Retrieving current prices for product {} query", product.getName());
    Mono<Price> price = priceClient.getCurrentPriceForProduct(product.getId());
    return price.block();
  }

  public List<Price> getPrices(Product product) {
    log.info("Retrieving all prices for product {} query", product.getName());
    Flux<Price> prices = priceClient.getAllPricesForProduct(product.getId());
    return prices.collectList().block();
  }

}
