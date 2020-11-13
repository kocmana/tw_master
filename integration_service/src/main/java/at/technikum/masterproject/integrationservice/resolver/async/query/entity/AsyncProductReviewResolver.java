package at.technikum.masterproject.integrationservice.resolver.async.query.entity;

import at.technikum.masterproject.integrationservice.client.customerservice.CustomerInformationClient;
import at.technikum.masterproject.integrationservice.client.productservice.ProductInformationClient;
import at.technikum.masterproject.integrationservice.model.customer.Customer;
import at.technikum.masterproject.integrationservice.model.product.Product;
import at.technikum.masterproject.integrationservice.model.product.ProductReview;
import graphql.kickstart.tools.GraphQLResolver;
import java.util.concurrent.CompletableFuture;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(prefix = "services", name = "resolver-mode", havingValue = "ASYNC")
@Slf4j
@RequiredArgsConstructor
public class AsyncProductReviewResolver implements GraphQLResolver<ProductReview> {

  private final ProductInformationClient productInformationClient;
  private final CustomerInformationClient customerInformationClient;

  public CompletableFuture<Product> getProduct(ProductReview productReview) {
    log.info("Retrieving product for review {}, resolving asynchronously", productReview.getId());
    return productInformationClient.getProductById(productReview.getId())
        .toFuture();
  }

  public CompletableFuture<Customer> getCustomer(ProductReview productReview) {
    log.info("Retrieving customer for review {}, resolving asynchronously", productReview.getId());
    return customerInformationClient.getCustomerById(productReview.getId())
        .toFuture();
  }

}
