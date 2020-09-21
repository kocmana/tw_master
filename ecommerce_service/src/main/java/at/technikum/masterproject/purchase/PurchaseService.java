package at.technikum.masterproject.purchase;

import at.technikum.masterproject.purchase.model.Purchase;
import at.technikum.masterproject.purchase.model.PurchaseNotFoundException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PurchaseService {

  private final PurchaseRepository purchaseRepository;

  @Autowired
  public PurchaseService(PurchaseRepository purchaseRepository) {
    this.purchaseRepository = purchaseRepository;
  }

  public List<Purchase> getPurchasesForCustomer(Integer customerId, Pageable pageable) {
    return purchaseRepository.findPurchasesByCustomerId(customerId);
  }

  public Purchase getPurchaseById(Long purchaseId) {
    return purchaseRepository.findById(purchaseId)
        .orElseThrow(() -> generatePurchaseNotFoundException(purchaseId));
  }

  public Long savePurchase(Purchase purchase) {
    Purchase savedPurchase = purchaseRepository.save(purchase);
    return savedPurchase.getId();
  }

  private PurchaseNotFoundException generatePurchaseNotFoundException(Long purchaseId) {
    String message = String.format("Purchase with ID %d not found", purchaseId);
    return new PurchaseNotFoundException(message);
  }
}
