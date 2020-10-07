package at.technikum.masterproject.integrationservice.client.customerservice;

import at.technikum.masterproject.integrationservice.model.customer.CustomerInteraction;
import at.technikum.masterproject.integrationservice.model.customer.CustomerNetwork;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CustomerNetworkClient {

  Flux<CustomerNetwork> getNetworkById(int customerId);

  Mono<CustomerInteraction> saveCustomerRelationship(CustomerInteraction customerInteraction);
}
