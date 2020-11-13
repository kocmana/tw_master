package at.technikum.masterproject.integrationservice.resolver.async.query.entity;

import at.technikum.masterproject.integrationservice.client.customerservice.CustomerNetworkClient;
import at.technikum.masterproject.integrationservice.client.ecommerceservice.PurchaseClient;
import at.technikum.masterproject.integrationservice.client.productservice.ProductReviewClient;
import at.technikum.masterproject.integrationservice.model.customer.Customer;
import at.technikum.masterproject.integrationservice.model.customer.CustomerNetwork;
import at.technikum.masterproject.integrationservice.model.ecommerce.Purchase;
import at.technikum.masterproject.integrationservice.model.product.ProductReview;
import graphql.kickstart.tools.GraphQLResolver;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(prefix = "services", name = "resolver-mode", havingValue = "ASYNC")
@Slf4j
@RequiredArgsConstructor
public class AsyncCustomerResolver implements GraphQLResolver<Customer> {

  private final ProductReviewClient productReviewClient;
  private final CustomerNetworkClient customerNetworkClient;
  private final PurchaseClient purchaseClient;

  public CompletableFuture<List<ProductReview>> getReviews(Customer customer) {
    log.info("Retrieving reviews for customer {}, resolving asynchronously", customer.getCustomerId());
    return productReviewClient.getAllProductReviewsByCustomer(customer.getCustomerId())
        .toFuture();
  }

  public CompletableFuture<List<CustomerNetwork>> getNetwork(Customer customer) {
    log.info("Retrieving network for customer {}, resolving asynchronously", customer.getCustomerId());
    return customerNetworkClient.getNetworkByCustomerId(customer.getCustomerId())
        .toFuture();
  }

  public CompletableFuture<List<Purchase>> getPurchases(Customer customer) {
    log.info("Retrieving purchases for customer {}, resolving asynchronously", customer.getCustomerId());
    return purchaseClient.getPurchasesForCustomer(customer.getCustomerId())
        .toFuture();
  }
}
