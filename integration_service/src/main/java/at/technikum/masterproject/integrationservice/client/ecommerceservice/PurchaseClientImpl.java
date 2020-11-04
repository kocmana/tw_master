package at.technikum.masterproject.integrationservice.client.ecommerceservice;

import at.technikum.masterproject.integrationservice.model.ecommerce.Purchase;
import at.technikum.masterproject.integrationservice.model.ecommerce.dto.CreatePurchaseInput;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@Slf4j
public class PurchaseClientImpl implements PurchaseClient {

  private static final String PURCHASE_ENDPOINT = "/purchase";
  private static final String PURCHASE_BY_PURCHASE_ID_ENDPOINT = "/purchase/{purchaseId}";
  private static final String PURCHASE_BY_CUSTOMER_ENDPOINT = "/purchase/customer/{customerId}";

  private final WebClient webClient;

  @Autowired
  public PurchaseClientImpl(@Qualifier("ecommerceServiceWebClient") WebClient webClient) {
    this.webClient = webClient;
  }

  @Override
  public Purchase getPurchase(int purchaseId) {
    return webClient.get()
        .uri(uriBuilder -> uriBuilder
            .pathSegment(PURCHASE_ENDPOINT)
            .pathSegment("{purchaseId}")
            .build(purchaseId))
        .retrieve()
        .bodyToMono(Purchase.class)
        .retry(2)
        .block();
  }

  @Override
  public List<Purchase> getPurchasesForCustomer(int customerId) {
    return webClient.get()
        .uri(uriBuilder -> uriBuilder
            .pathSegment(PURCHASE_ENDPOINT)
            .pathSegment("/customer")
            .pathSegment("{customerId}")
            .build(customerId))
        .retrieve()
        .bodyToFlux(Purchase.class)
        .retry(2)
        .collectList()
        .block();
  }

  @Override
  public long savePurchase(CreatePurchaseInput purchase) {
    return webClient.post()
        .uri(uriBuilder -> uriBuilder
            .pathSegment(PURCHASE_ENDPOINT)
            .build())
        .bodyValue(purchase)
        .retrieve()
        .bodyToMono(Purchase.class)
        .map(Purchase::getId)
        .block();
  }

}
