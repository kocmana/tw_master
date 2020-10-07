package at.technikum.masterproject.integrationservice.customerservice;

import static org.springframework.http.HttpMethod.PATCH;

import at.technikum.masterproject.integrationservice.model.customer.Customer;
import at.technikum.masterproject.integrationservice.model.product.Product;
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
public class CustomerInformationClientImpl implements CustomerInformationClient {

  private static final String CUSTOMER_ENDPOINT = "customer";

  private final WebClient webClient;
  private final Consumer<? super Throwable> handleError = exception -> log.info("Product service call failed: {}",
      exception.getMessage());

  @Autowired
  public CustomerInformationClientImpl(@Qualifier("customerServiceWebClient") WebClient webClient) {
    this.webClient = webClient;
  }

  @Override
  public Mono<Customer> getCustomerById(int id) {
    return webClient.get()
        .uri(uriBuilder -> uriBuilder
            .path(CUSTOMER_ENDPOINT)
            .pathSegment("{id}")
            .build(id))
        .retrieve()
        .bodyToMono(Customer.class)
        .doOnError(handleError);
  }

  @Override
  public Flux<Customer> getAllCustomer() {
    return webClient.get()
        .uri(uriBuilder -> uriBuilder
            .path(CUSTOMER_ENDPOINT)
            .build())
        .retrieve()
        .bodyToFlux(Customer.class)
        .doOnError(handleError);
  }

  @Override
  public Mono<Customer> saveCustomer(Product product) {
    return webClient.post()
        .uri(uriBuilder -> uriBuilder
            .path(CUSTOMER_ENDPOINT)
            .build())
        .bodyValue(product)
        .retrieve()
        .bodyToMono(Customer.class)
        .doOnError(handleError);
  }

  @Override
  public Mono<Customer> updateCustomer(Product product) {
    return webClient.method(PATCH)
        .uri(uriBuilder -> uriBuilder
            .path(CUSTOMER_ENDPOINT)
            .build())
        .bodyValue(product)
        .retrieve()
        .bodyToMono(Customer.class)
        .doOnError(handleError);
  }

}
