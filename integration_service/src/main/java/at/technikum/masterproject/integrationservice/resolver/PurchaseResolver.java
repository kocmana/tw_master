package at.technikum.masterproject.integrationservice.resolver;

import at.technikum.masterproject.integrationservice.client.customerservice.CustomerInformationClient;
import at.technikum.masterproject.integrationservice.model.customer.Customer;
import at.technikum.masterproject.integrationservice.model.ecommerce.Purchase;
import graphql.kickstart.tools.GraphQLResolver;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@Slf4j
public class PurchaseResolver implements GraphQLResolver<Purchase> {

  private final CustomerInformationClient customerInformationClient;

  @Autowired
  public PurchaseResolver(
      CustomerInformationClient customerInformationClient) {
    this.customerInformationClient = customerInformationClient;
  }

  public Customer getCustomer(Purchase purchase) {
    Mono<Customer> customer = customerInformationClient.getCustomerById(purchase.getCustomerId());
    return customer.block();
  }
}