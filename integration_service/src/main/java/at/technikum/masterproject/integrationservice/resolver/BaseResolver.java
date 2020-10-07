package at.technikum.masterproject.integrationservice.resolver;

import at.technikum.masterproject.integrationservice.client.customerservice.CustomerInformationClient;
import at.technikum.masterproject.integrationservice.client.customerservice.CustomerNetworkClient;
import at.technikum.masterproject.integrationservice.client.productservice.ProductInformationClient;
import at.technikum.masterproject.integrationservice.client.productservice.ProductReviewClient;
import at.technikum.masterproject.integrationservice.model.customer.Customer;
import at.technikum.masterproject.integrationservice.model.customer.CustomerNetwork;
import at.technikum.masterproject.integrationservice.model.product.Product;
import at.technikum.masterproject.integrationservice.model.product.ProductReview;
import graphql.kickstart.tools.GraphQLQueryResolver;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
@Slf4j
public class BaseResolver implements GraphQLQueryResolver {

  private final ProductInformationClient productInformationClient;
  private final ProductReviewClient productReviewClient;
  private final CustomerInformationClient customerInformationClient;
  private final CustomerNetworkClient customerNetworkClient;

  @Autowired
  public BaseResolver(
      ProductInformationClient productInformationClient,
      ProductReviewClient productReviewClient,
      CustomerInformationClient customerInformationClient,
      CustomerNetworkClient customerNetworkClient) {
    this.productInformationClient = productInformationClient;
    this.productReviewClient = productReviewClient;
    this.customerInformationClient = customerInformationClient;
    this.customerNetworkClient = customerNetworkClient;
  }

  public List<Product> products() {
    log.info("Retrieving all products query");
    Flux<Product> product = productInformationClient.getAllProducts();
    return product.collectList().block();
  }

  public Product product(Integer id) {
    log.info("Retrieved product query for id {}", id);
    Mono<Product> product = productInformationClient.getProductById(id);
    return product.block();
  }

  public List<ProductReview> reviews() {
    log.info("Retrieving all product reviews query");
    Flux<ProductReview> productReviews = productReviewClient.getAllProductReviews();
    return productReviews.collectList().block();
  }

  public List<Customer> customers() {
    log.info("Retrieving all customers query");
    Flux<Customer> productReviews = customerInformationClient.getAllCustomer();
    return productReviews.collectList().block();
  }

  public Customer customer(Integer customerId) {
    log.info("Retrieved customer query for customerId {}", customerId);
    Mono<Customer> customer = customerInformationClient.getCustomerById(customerId);
    return customer.block();
  }

  public List<CustomerNetwork> customerNetwork(Integer customerId) {
    log.info("Retrieved customer network query for customerId {}", customerId);
    Flux<CustomerNetwork> customerNetworks = customerNetworkClient.getNetworkById(customerId);
    return customerNetworks.collectList().block();
  }

}
