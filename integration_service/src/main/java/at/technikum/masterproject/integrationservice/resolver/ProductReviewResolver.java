package at.technikum.masterproject.integrationservice.resolver;

import at.technikum.masterproject.integrationservice.model.product.Product;
import at.technikum.masterproject.integrationservice.model.product.ProductReview;
import at.technikum.masterproject.integrationservice.productservice.ProductInformationClient;
import graphql.kickstart.tools.GraphQLResolver;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@Slf4j
public class ProductReviewResolver implements GraphQLResolver<ProductReview> {

  private final ProductInformationClient productInformationClient;

  @Autowired
  public ProductReviewResolver(
      ProductInformationClient productInformationClient) {
    this.productInformationClient = productInformationClient;
  }

  public Product getProduct(ProductReview productReview) {
    Mono<Product> product = productInformationClient.getProductById(productReview.getId());
    return product.block();
  }

}
