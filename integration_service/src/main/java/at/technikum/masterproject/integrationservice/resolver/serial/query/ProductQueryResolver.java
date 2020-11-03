package at.technikum.masterproject.integrationservice.resolver.serial.query;

import at.technikum.masterproject.integrationservice.client.productservice.ProductInformationClient;
import at.technikum.masterproject.integrationservice.client.productservice.ProductReviewClient;
import at.technikum.masterproject.integrationservice.model.product.Product;
import at.technikum.masterproject.integrationservice.model.product.ProductReview;
import graphql.kickstart.tools.GraphQLQueryResolver;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(prefix = "services", name = "resolver-mode", havingValue = "SERIAL", matchIfMissing = true)
@Slf4j
@RequiredArgsConstructor
public class ProductQueryResolver implements GraphQLQueryResolver {

  private final ProductInformationClient productInformationClient;
  private final ProductReviewClient productReviewClient;

  public List<Product> products() {
    log.info("Retrieving all products query");
    return productInformationClient.getAllProducts();
  }

  public Product product(int id) {
    log.info("Retrieved product query for id {}", id);
    return productInformationClient.getProductById(id);
  }

  public List<ProductReview> reviews() {
    log.info("Retrieving all product reviews query");
    return productReviewClient.getAllProductReviews();
  }

}
