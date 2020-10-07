package at.technikum.masterproject.integrationservice.client.ecommerceservice;

import at.technikum.masterproject.integrationservice.model.customer.CustomerInteraction;
import at.technikum.masterproject.integrationservice.model.ecommerce.Purchase;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface PurchaseClient {

  Mono<Purchase> getPurchase(int purchaseId);

  Flux<Purchase> getPurchasesForCustomer(int customerId);

  Mono<CustomerInteraction> savePurchase(
      CustomerInteraction customerInteraction);
}
