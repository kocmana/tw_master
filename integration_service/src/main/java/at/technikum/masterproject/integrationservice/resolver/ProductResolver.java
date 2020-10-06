package at.technikum.masterproject.integrationservice.resolver;

import at.technikum.masterproject.integrationservice.model.Product;
import at.technikum.masterproject.integrationservice.productservice.ProductServiceClient;
import graphql.kickstart.tools.GraphQLQueryResolver;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@Slf4j
public class ProductResolver implements GraphQLQueryResolver {

  private final ProductServiceClient productServiceClient;

  @Autowired
  public ProductResolver(
      ProductServiceClient productServiceClient) {
    this.productServiceClient = productServiceClient;
  }

  public Product product(Integer id) {
    log.info("Retrieved product query for id {}", id);
    Mono<Product> product = productServiceClient.getProduct(id);
    return product.block();
  }
}
