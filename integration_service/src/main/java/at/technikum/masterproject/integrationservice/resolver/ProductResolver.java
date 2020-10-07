package at.technikum.masterproject.integrationservice.resolver;

import at.technikum.masterproject.integrationservice.client.productservice.ProductReviewClient;
import at.technikum.masterproject.integrationservice.model.product.Product;
import at.technikum.masterproject.integrationservice.model.product.ProductReview;
import graphql.kickstart.tools.GraphQLResolver;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

@Component
@Slf4j
public class ProductResolver implements GraphQLResolver<Product> {

  private final ProductReviewClient productReviewClient;

  @Autowired
  public ProductResolver(
      ProductReviewClient productReviewClient) {
    this.productReviewClient = productReviewClient;
  }

  public List<ProductReview> getReviewsForProduct(final Product product) {
    log.info("Retrieving all product reviews for product {} query", product.getName());
    Flux<ProductReview> productReviews = productReviewClient.getAllProductReviewsForProduct(product.getId());
    return productReviews.collectList().block();
  }

}
