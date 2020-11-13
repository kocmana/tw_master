package at.technikum.masterproject.integrationservice.client.customerservice;

import at.technikum.masterproject.integrationservice.model.customer.CustomerInteraction;
import at.technikum.masterproject.integrationservice.model.customer.CustomerNetwork;
import at.technikum.masterproject.integrationservice.model.customer.dto.CreateCustomerInteractionInput;
import java.util.List;
import reactor.core.publisher.Mono;

public interface CustomerNetworkClient {

  Mono<List<CustomerNetwork>> getNetworkByCustomerId(int customerId);

  Mono<CustomerInteraction> saveCustomerInteraction(CreateCustomerInteractionInput customerInteraction);
}
