package at.technikum.masterproject.ecommerce.purchase;

import at.technikum.masterproject.ecommerce.purchase.model.Purchase;
import at.technikum.masterproject.ecommerce.purchase.model.PurchaseNotFoundException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
class PurchaseService {

  private final PurchaseRepository purchaseRepository;

  @Autowired
  PurchaseService(PurchaseRepository purchaseRepository) {
    this.purchaseRepository = purchaseRepository;
  }

  List<Purchase> getPurchasesForCustomer(int customerId) {
    return purchaseRepository.findPurchasesByCustomerId(customerId);
  }

  Purchase getPurchaseById(long purchaseId) {
    return purchaseRepository.findById(purchaseId)
        .orElseThrow(() -> generatePurchaseNotFoundException(purchaseId));
  }

  Long savePurchase(Purchase purchase) {
    Purchase savedPurchase = purchaseRepository.save(purchase);
    return savedPurchase.getId();
  }

  PurchaseNotFoundException generatePurchaseNotFoundException(long purchaseId) {
    return new PurchaseNotFoundException(purchaseId);
  }
}
