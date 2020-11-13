package at.technikum.masterproject.integrationservice.client.ecommerceservice;

import at.technikum.masterproject.integrationservice.model.ecommerce.Purchase;
import at.technikum.masterproject.integrationservice.model.ecommerce.dto.CreatePurchaseInput;
import java.util.List;
import reactor.core.publisher.Mono;

public interface PurchaseClient {

  Mono<Purchase> getPurchase(int purchaseId);

  Mono<List<Purchase>> getPurchasesForCustomer(int customerId);

  Mono<Long> savePurchase(CreatePurchaseInput purchase);
}
