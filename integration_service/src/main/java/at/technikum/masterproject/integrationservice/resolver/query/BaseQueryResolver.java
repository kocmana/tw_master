package at.technikum.masterproject.integrationservice.resolver.query;

import at.technikum.masterproject.integrationservice.client.customerservice.CustomerInformationClient;
import at.technikum.masterproject.integrationservice.client.customerservice.CustomerNetworkClient;
import at.technikum.masterproject.integrationservice.client.ecommerceservice.PurchaseClient;
import at.technikum.masterproject.integrationservice.client.productservice.ProductInformationClient;
import at.technikum.masterproject.integrationservice.client.productservice.ProductReviewClient;
import at.technikum.masterproject.integrationservice.model.customer.Customer;
import at.technikum.masterproject.integrationservice.model.customer.CustomerNetwork;
import at.technikum.masterproject.integrationservice.model.ecommerce.Purchase;
import at.technikum.masterproject.integrationservice.model.product.Product;
import at.technikum.masterproject.integrationservice.model.product.ProductReview;
import graphql.kickstart.tools.GraphQLQueryResolver;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class BaseQueryResolver implements GraphQLQueryResolver {

  private final ProductInformationClient productInformationClient;
  private final ProductReviewClient productReviewClient;
  private final CustomerInformationClient customerInformationClient;
  private final CustomerNetworkClient customerNetworkClient;
  private final PurchaseClient purchaseClient;

  @Autowired
  public BaseQueryResolver(
      ProductInformationClient productInformationClient,
      ProductReviewClient productReviewClient,
      CustomerInformationClient customerInformationClient,
      CustomerNetworkClient customerNetworkClient,
      PurchaseClient purchaseClient) {
    this.productInformationClient = productInformationClient;
    this.productReviewClient = productReviewClient;
    this.customerInformationClient = customerInformationClient;
    this.customerNetworkClient = customerNetworkClient;
    this.purchaseClient = purchaseClient;
  }

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

  public List<Customer> customers() {
    log.info("Retrieving all customers query");
    return customerInformationClient.getAllCustomer();
  }

  public Customer customer(int customerId) {
    log.info("Retrieved customer query for customerId {}", customerId);
    return customerInformationClient.getCustomerById(customerId);
  }

  public List<CustomerNetwork> customerNetwork(int customerId) {
    log.info("Retrieved customer network query for customerId {}", customerId);
    return customerNetworkClient.getNetworkById(customerId);
  }

  public Purchase purchase(Integer purchaseId) {
    log.info("Retrieved purchase query for purchaseId {}", purchaseId);
    return purchaseClient.getPurchase(purchaseId);
  }

}
