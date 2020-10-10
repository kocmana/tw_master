package at.technikum.masterproject.integrationservice.client.customerservice;

import static org.springframework.http.HttpMethod.GET;

import at.technikum.masterproject.integrationservice.model.customer.Customer;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class CustomerInformationClientImpl implements CustomerInformationClient {

  private static final String CUSTOMER_ENDPOINT = "/customer";
  private static final String CUSTOMER_BY_CUSTOMER_ID_ENDPOINT = "/customer/{customerId}";

  private final RestTemplate restTemplate;

  public CustomerInformationClientImpl(
      @Qualifier("customerServiceRestTemplate") RestTemplate restTemplate) {
    this.restTemplate = restTemplate;
  }

  @Override
  public Customer getCustomerById(int customerId) {
    return restTemplate.getForObject(
        CUSTOMER_BY_CUSTOMER_ID_ENDPOINT,
        Customer.class,
        customerId);
  }

  @Override
  public List<Customer> getAllCustomer() {
    ResponseEntity<List<Customer>> response = restTemplate.exchange(
        CUSTOMER_ENDPOINT,
        GET,
        HttpEntity.EMPTY,
        new ParameterizedTypeReference<List<Customer>>() {
        });

    return Optional.ofNullable(response.getBody())
        .orElse(Collections.emptyList());
  }

  @Override
  public Customer saveCustomer(Customer customer) {
    return restTemplate.postForObject(CUSTOMER_ENDPOINT,
        customer,
        Customer.class);
  }

  @Override
  public Customer updateCustomer(Customer customer) {
    return restTemplate.postForObject(CUSTOMER_ENDPOINT,
        customer,
        Customer.class);
  }

}
