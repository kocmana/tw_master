package at.technikum.masterproject.integrationservice.resolver.query;

import at.technikum.masterproject.integrationservice.client.customerservice.CustomerInformationClient;
import at.technikum.masterproject.integrationservice.client.customerservice.CustomerNetworkClient;
import at.technikum.masterproject.integrationservice.model.customer.CustomerInteraction;
import at.technikum.masterproject.integrationservice.model.customer.dto.CreateCustomerInput;
import at.technikum.masterproject.integrationservice.model.customer.dto.CreateCustomerInteractionInput;
import at.technikum.masterproject.integrationservice.model.customer.dto.UpdateCustomerInput;
import graphql.kickstart.tools.GraphQLMutationResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CustomerMutationResolver implements GraphQLMutationResolver {

  private final CustomerInformationClient customerInformationClient;
  private final CustomerNetworkClient customerNetworkClient;

  @Autowired
  public CustomerMutationResolver(
      CustomerInformationClient customerInformationClient,
      CustomerNetworkClient customerNetworkClient) {
    this.customerInformationClient = customerInformationClient;
    this.customerNetworkClient = customerNetworkClient;
  }

  public int createCustomer(CreateCustomerInput customer) {
    return customerInformationClient.saveCustomer(customer);
  }

  public void updateCustomer(UpdateCustomerInput customer) {
    customerInformationClient.updateCustomer(customer);
  }

  public void deleteCustomer(int customerId) {
    customerInformationClient.deleteCustomer(customerId);
  }

  public CustomerInteraction createCustomerInteraction(CreateCustomerInteractionInput customerInteraction){
    return customerNetworkClient.saveCustomerInteraction(customerInteraction);
  }
}
