package at.technikum.masterproject.integrationservice.client.customerservice;

import at.technikum.masterproject.integrationservice.model.customer.CustomerInteraction;
import at.technikum.masterproject.integrationservice.model.customer.CustomerNetwork;
import at.technikum.masterproject.integrationservice.model.customer.dto.CreateCustomerInteractionInput;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class CustomerNetworkClientImpl implements CustomerNetworkClient {

  private static final String NETWORK_ENDPOINT = "network";

  private final WebClient webClient;

  @Autowired
  public CustomerNetworkClientImpl(@Qualifier("customerServiceWebClient") WebClient webClient) {
    this.webClient = webClient;
  }

  @Override
  public Mono<List<CustomerNetwork>> getNetworkByCustomerId(int customerId) {
    return webClient.get()
        .uri(uriBuilder -> uriBuilder
            .pathSegment(NETWORK_ENDPOINT)
            .pathSegment("{customerId}")
            .build(customerId))
        .retrieve()
        .bodyToFlux(CustomerNetwork.class)
        .retry(2)
        .collectList();
  }

  @Override
  public Mono<CustomerInteraction> saveCustomerInteraction(CreateCustomerInteractionInput customerInteraction) {
    return webClient.post()
        .uri(uriBuilder -> uriBuilder
            .pathSegment(NETWORK_ENDPOINT)
            .build())
        .bodyValue(customerInteraction)
        .retrieve()
        .bodyToMono(CustomerInteraction.class)
        .retry(2);
  }

}
