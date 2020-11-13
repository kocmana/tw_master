package at.technikum.masterproject.integrationservice.resolver.serial.query.entity;

import at.technikum.masterproject.integrationservice.client.customerservice.CustomerInformationClient;
import at.technikum.masterproject.integrationservice.model.customer.Customer;
import at.technikum.masterproject.integrationservice.model.ecommerce.Purchase;
import graphql.kickstart.tools.GraphQLResolver;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(prefix = "services", name = "resolver-mode", havingValue = "SERIAL", matchIfMissing = true)
@Slf4j
@RequiredArgsConstructor
public class PurchaseResolver implements GraphQLResolver<Purchase> {

  private final CustomerInformationClient customerInformationClient;

  public Customer getCustomer(Purchase purchase) {
    return customerInformationClient.getCustomerById(purchase.getCustomerId())
        .block();
  }
}
