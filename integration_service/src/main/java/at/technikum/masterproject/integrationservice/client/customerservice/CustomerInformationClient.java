package at.technikum.masterproject.integrationservice.client.customerservice;

import at.technikum.masterproject.integrationservice.model.customer.Customer;
import at.technikum.masterproject.integrationservice.model.customer.dto.CreateCustomerInput;
import at.technikum.masterproject.integrationservice.model.customer.dto.UpdateCustomerInput;
import java.util.List;
import reactor.core.publisher.Mono;

public interface CustomerInformationClient {

  Mono<Customer> getCustomerById(int id);

  Mono<List<Customer>> getAllCustomer();

  Mono<Integer> saveCustomer(CreateCustomerInput customer);

  void updateCustomer(UpdateCustomerInput customer);

  void deleteCustomer(int customerId);
}
