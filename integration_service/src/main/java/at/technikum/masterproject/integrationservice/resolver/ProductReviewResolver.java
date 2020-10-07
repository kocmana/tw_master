package at.technikum.masterproject.integrationservice.resolver;

import at.technikum.masterproject.integrationservice.client.customerservice.CustomerInformationClient;
import at.technikum.masterproject.integrationservice.client.productservice.ProductInformationClient;
import at.technikum.masterproject.integrationservice.model.customer.Customer;
import at.technikum.masterproject.integrationservice.model.product.Product;
import at.technikum.masterproject.integrationservice.model.product.ProductReview;
import graphql.kickstart.tools.GraphQLResolver;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@Slf4j
public class ProductReviewResolver implements GraphQLResolver<ProductReview> {

  private final ProductInformationClient productInformationClient;
  private final CustomerInformationClient customerInformationClient;

  @Autowired
  public ProductReviewResolver(
      ProductInformationClient productInformationClient,
      CustomerInformationClient customerInformationClient) {
    this.productInformationClient = productInformationClient;
    this.customerInformationClient = customerInformationClient;
  }

  public Product getProduct(ProductReview productReview) {
    Mono<Product> product = productInformationClient.getProductById(productReview.getId());
    return product.block();
  }

  public Customer getCustomer(ProductReview productReview) {
    Mono<Customer> customer = customerInformationClient.getCustomerById(productReview.getId());
    return customer.block();
  }

}
