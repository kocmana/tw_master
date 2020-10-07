package at.technikum.masterproject.integrationservice.client.customerservice;

import at.technikum.masterproject.integrationservice.model.customer.CustomerInteraction;
import at.technikum.masterproject.integrationservice.model.customer.CustomerNetwork;
import java.util.function.Consumer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class CustomerNetworkClientImpl implements CustomerNetworkClient {

  private static final String NETWORK_ENDPOINT = "network";

  private final WebClient webClient;
  private final Consumer<? super Throwable> handleError = exception -> log.info("Product service call failed: {}",
      exception.getMessage());

  @Autowired
  public CustomerNetworkClientImpl(@Qualifier("customerServiceWebClient") WebClient webClient) {
    this.webClient = webClient;
  }

  @Override
  public Flux<CustomerNetwork> getNetworkById(int customerId) {
    return webClient.get()
        .uri(uriBuilder -> uriBuilder
            .path(NETWORK_ENDPOINT)
            .pathSegment("{customerId}")
            .build(customerId))
        .retrieve()
        .bodyToFlux(CustomerNetwork.class)
        .doOnError(handleError);
  }

  @Override
  public Mono<CustomerInteraction> saveCustomerRelationship(CustomerInteraction customerInteraction) {
    return webClient.post()
        .uri(uriBuilder -> uriBuilder
            .path(NETWORK_ENDPOINT)
            .build())
        .bodyValue(customerInteraction)
        .retrieve()
        .bodyToMono(CustomerInteraction.class)
        .doOnError(handleError);
  }

}
