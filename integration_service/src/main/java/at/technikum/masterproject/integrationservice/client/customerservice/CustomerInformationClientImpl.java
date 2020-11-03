package at.technikum.masterproject.integrationservice.client.customerservice;

import at.technikum.masterproject.integrationservice.model.customer.Customer;
import at.technikum.masterproject.integrationservice.model.customer.dto.CreateCustomerInput;
import at.technikum.masterproject.integrationservice.model.customer.dto.UpdateCustomerInput;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
@Slf4j
public class CustomerInformationClientImpl implements CustomerInformationClient {

  private static final String CUSTOMER_ENDPOINT = "/customer";

  private final WebClient webClient;

  public CustomerInformationClientImpl(@Qualifier("customerServiceWebClient") WebClient webClient) {
    this.webClient = webClient;
  }

  @Override
  public Customer getCustomerById(int customerId) {
    return webClient.get()
            .uri(uriBuilder -> uriBuilder
                    .path(CUSTOMER_ENDPOINT)
                    .pathSegment("{customerId}")
                    .build(customerId))
            .retrieve()
            .bodyToMono(Customer.class)
            .retry(2)
            .block();
  }

  @Override
  public List<Customer> getAllCustomer() {
    return webClient.get()
            .uri(uriBuilder -> uriBuilder
                    .path(CUSTOMER_ENDPOINT)
                    .build())
            .retrieve()
            .bodyToFlux(Customer.class)
            .retry(2)
            .collectList()
            .block();
  }

  @Override
  public int saveCustomer(CreateCustomerInput customer) {
    return webClient.post()
            .uri(uriBuilder -> uriBuilder
                    .path(CUSTOMER_ENDPOINT)
                    .build())
            .bodyValue(customer)
            .retrieve()
            .bodyToMono(Customer.class)
            .retry(2)
            .map(Customer::getCustomerId)
            .block();
  }

  @Override
  public void updateCustomer(UpdateCustomerInput customer) {
    webClient.put()
            .uri(uriBuilder -> uriBuilder
                    .path(CUSTOMER_ENDPOINT)
                    .build())
            .bodyValue(customer)
            .retrieve()
            .toBodilessEntity()
            .retry(2)
            .block();
  }

  @Override
  public void deleteCustomer(int customerId) {
    webClient.put()
            .uri(uriBuilder -> uriBuilder
                    .path(CUSTOMER_ENDPOINT)
                    .pathSegment("{customerId}")
                    .build(customerId))
            .retrieve()
            .toBodilessEntity()
            .retry(2)
            .block();
  }
}
