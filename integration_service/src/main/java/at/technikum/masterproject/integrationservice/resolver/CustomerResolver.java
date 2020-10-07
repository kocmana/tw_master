package at.technikum.masterproject.integrationservice.resolver;

import at.technikum.masterproject.integrationservice.model.customer.Customer;
import at.technikum.masterproject.integrationservice.model.product.ProductReview;
import at.technikum.masterproject.integrationservice.productservice.ProductReviewClient;
import graphql.kickstart.tools.GraphQLResolver;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

@Component
public class CustomerResolver implements GraphQLResolver<Customer> {

  private final ProductReviewClient productReviewClient;

  @Autowired
  public CustomerResolver(ProductReviewClient productReviewClient) {
    this.productReviewClient = productReviewClient;
  }

  public List<ProductReview> getReviewsOfCustomer(Customer customer) {
    Flux<ProductReview> reviews = productReviewClient
        .getAllProductReviewsByCustomer(customer.getCustomerId());
    return reviews.collectList().block();
  }
}
