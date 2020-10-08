package at.technikum.masterproject.integrationservice.resolver;

import at.technikum.masterproject.integrationservice.client.customerservice.CustomerNetworkClient;
import at.technikum.masterproject.integrationservice.client.ecommerceservice.PurchaseClient;
import at.technikum.masterproject.integrationservice.client.productservice.ProductReviewClient;
import at.technikum.masterproject.integrationservice.model.customer.Customer;
import at.technikum.masterproject.integrationservice.model.customer.CustomerNetwork;
import at.technikum.masterproject.integrationservice.model.ecommerce.Purchase;
import at.technikum.masterproject.integrationservice.model.product.ProductReview;
import graphql.kickstart.tools.GraphQLResolver;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

@Component
public class CustomerResolver implements GraphQLResolver<Customer> {

  private final ProductReviewClient productReviewClient;
  private final CustomerNetworkClient customerNetworkClient;
  private final PurchaseClient purchaseClient;

  @Autowired
  public CustomerResolver(ProductReviewClient productReviewClient,
      CustomerNetworkClient customerNetworkClient,
      PurchaseClient purchaseClient) {
    this.productReviewClient = productReviewClient;
    this.customerNetworkClient = customerNetworkClient;
    this.purchaseClient = purchaseClient;
  }

  public List<ProductReview> getReviews(Customer customer) {
    Flux<ProductReview> reviews = productReviewClient
        .getAllProductReviewsByCustomer(customer.getCustomerId());
    return reviews.collectList().block();
  }

  public List<CustomerNetwork> getNetwork(Customer customer) {
    Flux<CustomerNetwork> customerNetwork = customerNetworkClient.getNetworkById(customer.getCustomerId());
    return customerNetwork.collectList().block();
  }

  public List<Purchase> getPurchases(Customer customer) {
    Flux<Purchase> purchases = purchaseClient.getPurchasesForCustomer(customer.getCustomerId());
    return purchases.collectList().block();
  }
}
