package at.technikum.masterproject.integrationservice.resolver.serial.query.entity;

import at.technikum.masterproject.integrationservice.client.customerservice.CustomerInformationClient;
import at.technikum.masterproject.integrationservice.client.productservice.ProductInformationClient;
import at.technikum.masterproject.integrationservice.model.customer.Customer;
import at.technikum.masterproject.integrationservice.model.product.Product;
import at.technikum.masterproject.integrationservice.model.product.ProductReview;
import graphql.kickstart.tools.GraphQLResolver;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(prefix = "services", name = "resolver-mode", havingValue = "SERIAL", matchIfMissing = true)
@Slf4j
@RequiredArgsConstructor
public class ProductReviewResolver implements GraphQLResolver<ProductReview> {

  private final ProductInformationClient productInformationClient;
  private final CustomerInformationClient customerInformationClient;

  public Product getProduct(ProductReview productReview) {
    return productInformationClient.getProductById(productReview.getId())
        .block();
  }

  public Customer getCustomer(ProductReview productReview) {
    return customerInformationClient.getCustomerById(productReview.getId())
        .block();
  }

}
