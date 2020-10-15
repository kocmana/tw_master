package at.technikum.masterproject.integrationservice.client.customerservice;

import at.technikum.masterproject.integrationservice.model.customer.CustomerInteraction;
import at.technikum.masterproject.integrationservice.model.customer.CustomerNetwork;
import at.technikum.masterproject.integrationservice.model.customer.CustomerServiceException;
import at.technikum.masterproject.integrationservice.model.customer.dto.CreateCustomerInteractionInput;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class CustomerNetworkClientImpl implements CustomerNetworkClient {

  private static final String NETWORK_ENDPOINT = "/network";
  private static final String NETWORK_BY_CUSTOMER_ENDPOINT = "/network/{customerId}";

  private final RestTemplate restTemplate;

  @Autowired
  public CustomerNetworkClientImpl(
      @Qualifier("customerServiceRestTemplate") RestTemplate restTemplate) {
    this.restTemplate = restTemplate;
  }

  @Override
  public List<CustomerNetwork> getNetworkById(int customerId) {
    ResponseEntity<List<CustomerNetwork>> response = restTemplate.exchange(
        NETWORK_BY_CUSTOMER_ENDPOINT,
        HttpMethod.GET,
        HttpEntity.EMPTY,
        new ParameterizedTypeReference<List<CustomerNetwork>>() {
        },
        customerId);

    return Optional.ofNullable(response.getBody())
        .orElse(Collections.emptyList());
  }

  @Override
  public CustomerInteraction saveCustomerInteraction(CreateCustomerInteractionInput customerInteraction) {
    CustomerInteraction response = restTemplate.postForObject(
        NETWORK_ENDPOINT,
        customerInteraction,
        CustomerInteraction.class);

    return Optional.ofNullable(response)
        .orElseThrow(() -> new CustomerServiceException("Could not extract response."));
  }

}
