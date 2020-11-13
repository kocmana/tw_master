package at.technikum.masterproject.integrationservice.resolver.serial.query;

import at.technikum.masterproject.integrationservice.client.customerservice.CustomerInformationClient;
import at.technikum.masterproject.integrationservice.client.customerservice.CustomerNetworkClient;
import at.technikum.masterproject.integrationservice.model.customer.Customer;
import at.technikum.masterproject.integrationservice.model.customer.CustomerNetwork;
import graphql.kickstart.tools.GraphQLQueryResolver;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(prefix = "services", name = "resolver-mode", havingValue = "SERIAL", matchIfMissing = true)
@Slf4j
@RequiredArgsConstructor
public class CustomerQueryResolver implements GraphQLQueryResolver {

  private final CustomerInformationClient customerInformationClient;
  private final CustomerNetworkClient customerNetworkClient;

  public List<Customer> customers() {
    log.info("Retrieving all customers query");
    return customerInformationClient.getAllCustomer()
        .block();
  }

  public Customer customer(int customerId) {
    log.info("Retrieved customer query for customerId {}", customerId);
    return customerInformationClient.getCustomerById(customerId)
        .block();
  }

  public List<CustomerNetwork> customerNetwork(int customerId) {
    log.info("Retrieved customer network query for customerId {}", customerId);
    return customerNetworkClient.getNetworkByCustomerId(customerId)
        .block();
  }

}
