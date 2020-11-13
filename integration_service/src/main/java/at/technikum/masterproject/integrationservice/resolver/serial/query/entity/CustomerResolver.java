package at.technikum.masterproject.integrationservice.resolver.serial.query.entity;

import at.technikum.masterproject.integrationservice.client.customerservice.CustomerNetworkClient;
import at.technikum.masterproject.integrationservice.client.ecommerceservice.PurchaseClient;
import at.technikum.masterproject.integrationservice.client.productservice.ProductReviewClient;
import at.technikum.masterproject.integrationservice.model.customer.Customer;
import at.technikum.masterproject.integrationservice.model.customer.CustomerNetwork;
import at.technikum.masterproject.integrationservice.model.ecommerce.Purchase;
import at.technikum.masterproject.integrationservice.model.product.ProductReview;
import graphql.kickstart.tools.GraphQLResolver;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(prefix = "services", name = "resolver-mode", havingValue = "SERIAL", matchIfMissing = true)
@RequiredArgsConstructor
public class CustomerResolver implements GraphQLResolver<Customer> {

  private final ProductReviewClient productReviewClient;
  private final CustomerNetworkClient customerNetworkClient;
  private final PurchaseClient purchaseClient;

  public List<ProductReview> getReviews(Customer customer) {
    return productReviewClient.getAllProductReviewsByCustomer(customer.getCustomerId())
        .block();
  }

  public List<CustomerNetwork> getNetwork(Customer customer) {
    return customerNetworkClient.getNetworkByCustomerId(customer.getCustomerId())
        .block();
  }

  public List<Purchase> getPurchases(Customer customer) {
    return purchaseClient.getPurchasesForCustomer(customer.getCustomerId())
        .block();
  }
}
