package at.technikum.masterproject.integrationservice.client.ecommerceservice;

import at.technikum.masterproject.integrationservice.model.ecommerce.Purchase;
import at.technikum.masterproject.integrationservice.model.ecommerce.dto.CreatePurchaseInput;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class PurchaseClientImpl implements PurchaseClient {

  private static final String PURCHASE_ENDPOINT = "purchase";

  private final WebClient webClient;

  @Autowired
  public PurchaseClientImpl(@Qualifier("ecommerceServiceWebClient") WebClient webClient) {
    this.webClient = webClient;
  }

  @Override
  public Mono<Purchase> getPurchase(int purchaseId) {
    return webClient.get()
        .uri(uriBuilder -> uriBuilder
            .pathSegment(PURCHASE_ENDPOINT)
            .pathSegment("{purchaseId}")
            .build(purchaseId))
        .retrieve()
        .bodyToMono(Purchase.class)
        .retry(2);
  }

  @Override
  public Mono<List<Purchase>> getPurchasesForCustomer(int customerId) {
    return webClient.get()
        .uri(uriBuilder -> uriBuilder
            .pathSegment(PURCHASE_ENDPOINT)
            .pathSegment("customer")
            .pathSegment("{customerId}")
            .build(customerId))
        .retrieve()
        .bodyToFlux(Purchase.class)
        .retry(2)
        .collectList();
  }

  @Override
  public Mono<Long> savePurchase(CreatePurchaseInput purchase) {
    return webClient.post()
        .uri(uriBuilder -> uriBuilder
            .pathSegment(PURCHASE_ENDPOINT)
            .build())
        .bodyValue(purchase)
        .retrieve()
        .bodyToMono(Purchase.class)
        .map(Purchase::getId);
  }

}
