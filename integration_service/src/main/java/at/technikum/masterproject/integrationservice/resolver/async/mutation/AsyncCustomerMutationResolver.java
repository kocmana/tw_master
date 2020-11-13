package at.technikum.masterproject.integrationservice.resolver.async.mutation;

import at.technikum.masterproject.integrationservice.client.customerservice.CustomerInformationClient;
import at.technikum.masterproject.integrationservice.client.customerservice.CustomerNetworkClient;
import at.technikum.masterproject.integrationservice.model.customer.CustomerInteraction;
import at.technikum.masterproject.integrationservice.model.customer.dto.CreateCustomerInput;
import at.technikum.masterproject.integrationservice.model.customer.dto.CreateCustomerInteractionInput;
import at.technikum.masterproject.integrationservice.model.customer.dto.UpdateCustomerInput;
import graphql.kickstart.tools.GraphQLMutationResolver;
import java.util.concurrent.CompletableFuture;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(prefix = "services", name = "resolver-mode", havingValue = "ASYNC")
@RequiredArgsConstructor
public class AsyncCustomerMutationResolver implements GraphQLMutationResolver {

  private final CustomerInformationClient customerInformationClient;
  private final CustomerNetworkClient customerNetworkClient;

  public CompletableFuture<Integer> createCustomer(CreateCustomerInput customer) {
    return customerInformationClient.saveCustomer(customer).toFuture();
  }

  public void updateCustomer(UpdateCustomerInput customer) {
    customerInformationClient.updateCustomer(customer);
  }

  public void deleteCustomer(int customerId) {
    customerInformationClient.deleteCustomer(customerId);
  }

  public CompletableFuture<CustomerInteraction> createCustomerInteraction(
      CreateCustomerInteractionInput customerInteraction) {
    return customerNetworkClient.saveCustomerInteraction(customerInteraction).toFuture();
  }
}
