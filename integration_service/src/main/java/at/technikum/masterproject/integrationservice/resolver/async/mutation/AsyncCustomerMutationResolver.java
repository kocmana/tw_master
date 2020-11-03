package at.technikum.masterproject.integrationservice.resolver.async.mutation;

import at.technikum.masterproject.integrationservice.client.customerservice.CustomerInformationClient;
import at.technikum.masterproject.integrationservice.client.customerservice.CustomerNetworkClient;
import at.technikum.masterproject.integrationservice.model.customer.CustomerInteraction;
import at.technikum.masterproject.integrationservice.model.customer.dto.CreateCustomerInput;
import at.technikum.masterproject.integrationservice.model.customer.dto.CreateCustomerInteractionInput;
import at.technikum.masterproject.integrationservice.model.customer.dto.UpdateCustomerInput;
import at.technikum.masterproject.integrationservice.resolver.async.ResolverExecutor;
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
  private final ResolverExecutor resolverExecutor;

  public CompletableFuture<Integer> createCustomer(CreateCustomerInput customer) {
    return resolverExecutor.resolve(() -> customerInformationClient.saveCustomer(customer));
  }

  public void updateCustomer(UpdateCustomerInput customer) {
    resolverExecutor.run(() -> customerInformationClient.updateCustomer(customer));
  }

  public void deleteCustomer(int customerId) {
    resolverExecutor.run(() -> customerInformationClient.deleteCustomer(customerId));
  }

  public CompletableFuture<CustomerInteraction> createCustomerInteraction(
      CreateCustomerInteractionInput customerInteraction) {
    return resolverExecutor.resolve(() -> customerNetworkClient.saveCustomerInteraction(customerInteraction));
  }
}
