package at.technikum.masterproject.ecommerce.purchase;

import at.technikum.masterproject.ecommerce.purchase.model.Purchase;
import at.technikum.masterproject.ecommerce.purchase.model.PurchaseNotFoundException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PurchaseService {

  private final PurchaseRepository purchaseRepository;

  @Autowired
  public PurchaseService(PurchaseRepository purchaseRepository) {
    this.purchaseRepository = purchaseRepository;
  }

  public List<Purchase> getPurchasesForCustomer(int customerId) {
    return purchaseRepository.findPurchasesByCustomerId(customerId);
  }

  public Purchase getPurchaseById(long purchaseId) {
    return purchaseRepository.findById(purchaseId)
        .orElseThrow(() -> generatePurchaseNotFoundException(purchaseId));
  }

  public Long savePurchase(Purchase purchase) {
    Purchase savedPurchase = purchaseRepository.save(purchase);
    return savedPurchase.getId();
  }

  private PurchaseNotFoundException generatePurchaseNotFoundException(long purchaseId) {
    return new PurchaseNotFoundException(purchaseId);
  }
}
