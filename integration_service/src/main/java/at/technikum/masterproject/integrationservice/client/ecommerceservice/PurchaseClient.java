package at.technikum.masterproject.integrationservice.client.ecommerceservice;

import at.technikum.masterproject.integrationservice.model.ecommerce.Purchase;
import at.technikum.masterproject.integrationservice.model.ecommerce.dto.CreatePurchaseInput;
import java.util.List;

public interface PurchaseClient {

  Purchase getPurchase(long purchaseId);

  List<Purchase> getPurchasesForCustomer(int customerId);

  long savePurchase(CreatePurchaseInput purchase);
}
