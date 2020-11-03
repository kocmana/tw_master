package at.technikum.masterproject.integrationservice.resolver.serial.mutation;

import at.technikum.masterproject.integrationservice.client.customerservice.CustomerInformationClient;
import at.technikum.masterproject.integrationservice.client.customerservice.CustomerNetworkClient;
import at.technikum.masterproject.integrationservice.model.customer.CustomerInteraction;
import at.technikum.masterproject.integrationservice.model.customer.dto.CreateCustomerInput;
import at.technikum.masterproject.integrationservice.model.customer.dto.CreateCustomerInteractionInput;
import at.technikum.masterproject.integrationservice.model.customer.dto.UpdateCustomerInput;
import graphql.kickstart.tools.GraphQLMutationResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(prefix = "services", name = "resolver-mode", havingValue = "SERIAL", matchIfMissing = true)
@RequiredArgsConstructor
public class CustomerMutationResolver implements GraphQLMutationResolver {

  private final CustomerInformationClient customerInformationClient;
  private final CustomerNetworkClient customerNetworkClient;

  public int createCustomer(CreateCustomerInput customer) {
    return customerInformationClient.saveCustomer(customer);
  }

  public void updateCustomer(UpdateCustomerInput customer) {
    customerInformationClient.updateCustomer(customer);
  }

  public void deleteCustomer(int customerId) {
    customerInformationClient.deleteCustomer(customerId);
  }

  public CustomerInteraction createCustomerInteraction(
      CreateCustomerInteractionInput customerInteraction) {
    return customerNetworkClient.saveCustomerInteraction(customerInteraction);
  }
}
