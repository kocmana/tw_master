package at.technikum.masterproject.integrationservice.client.customerservice;

import at.technikum.masterproject.integrationservice.model.customer.Customer;
import at.technikum.masterproject.integrationservice.model.product.Product;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CustomerInformationClient {

  Mono<Customer> getCustomerById(int id);

  Flux<Customer> getAllCustomer();

  Mono<Customer> saveCustomer(Product product);

  Mono<Customer> updateCustomer(Product product);
}
