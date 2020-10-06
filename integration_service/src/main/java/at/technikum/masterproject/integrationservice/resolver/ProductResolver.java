package at.technikum.masterproject.integrationservice.resolver;

import at.technikum.masterproject.integrationservice.model.Product;
import graphql.kickstart.tools.GraphQLQueryResolver;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ProductResolver implements GraphQLQueryResolver {

  public Product product(Integer id) {
    log.info("Retrieved product query for id {}", id);
    return new Product(1, "someProduct", "someDescription", 1.0f);
  }
}
