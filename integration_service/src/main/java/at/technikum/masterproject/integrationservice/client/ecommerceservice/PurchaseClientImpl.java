package at.technikum.masterproject.integrationservice.client.ecommerceservice;

import at.technikum.masterproject.integrationservice.model.customer.CustomerInteraction;
import at.technikum.masterproject.integrationservice.model.ecommerce.Purchase;
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
public class PurchaseClientImpl implements PurchaseClient {

  private static final String PURCHASE_ENDPOINT = "purchase";
  private static final String PURCHASE_BY_CUSTOMER_ENDPOINT = "purchase/customer";

  private final WebClient webClient;
  private final Consumer<? super Throwable> handleError = exception -> log
      .info("eCommerce service call failed: {}",
          exception.getMessage());

  @Autowired
  public PurchaseClientImpl(@Qualifier("ecommerceServiceWebClient") WebClient webClient) {
    this.webClient = webClient;
  }

  @Override
  public Mono<Purchase> getPurchase(int purchaseId) {
    return webClient.get()
        .uri(uriBuilder -> uriBuilder
            .path(PURCHASE_BY_CUSTOMER_ENDPOINT)
            .pathSegment("{purchaseId}")
            .build(purchaseId))
        .retrieve()
        .bodyToMono(Purchase.class)
        .doOnError(handleError);
  }

  @Override
  public Flux<Purchase> getPurchasesForCustomer(int customerId) {
    return webClient.get()
        .uri(uriBuilder -> uriBuilder
            .path(PURCHASE_BY_CUSTOMER_ENDPOINT)
            .pathSegment("{customerId}")
            .build(customerId))
        .retrieve()
        .bodyToFlux(Purchase.class)
        .doOnError(handleError);
  }

  @Override
  public Mono<CustomerInteraction> savePurchase(
      CustomerInteraction customerInteraction) {
    return webClient.post()
        .uri(uriBuilder -> uriBuilder
            .path(PURCHASE_ENDPOINT)
            .build())
        .bodyValue(customerInteraction)
        .retrieve()
        .bodyToMono(CustomerInteraction.class)
        .doOnError(handleError);
  }

}
