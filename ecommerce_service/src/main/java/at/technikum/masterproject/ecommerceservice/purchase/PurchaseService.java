package at.technikum.masterproject.ecommerceservice.purchase;

import at.technikum.masterproject.ecommerceservice.price.PriceService;
import at.technikum.masterproject.ecommerceservice.price.model.domain.Price;
import at.technikum.masterproject.ecommerceservice.purchase.model.PurchaseNotFoundException;
import at.technikum.masterproject.ecommerceservice.purchase.model.domain.Purchase;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
class PurchaseService {

  private final PurchaseRepository purchaseRepository;
  private final PriceService priceService;

  @Autowired
  PurchaseService(PurchaseRepository purchaseRepository,
      PriceService priceService) {
    this.purchaseRepository = purchaseRepository;
    this.priceService = priceService;
  }

  List<Purchase> getPurchasesForCustomer(int customerId) {
    return purchaseRepository.findPurchasesByCustomerId(customerId);
  }

  Purchase getPurchaseById(int purchaseId) {
    return purchaseRepository.findById(purchaseId)
        .orElseThrow(() -> generatePurchaseNotFoundException(purchaseId));
  }

  int savePurchase(Purchase purchase) {
    Purchase updatedPurchaseInformation = updatePurchaseItemInformation(purchase);
    Purchase savedPurchase = purchaseRepository.save(updatedPurchaseInformation);
    return savedPurchase.getId();
  }

  //not pure!
  private Purchase updatePurchaseItemInformation(Purchase purchase) {
    purchase.getItems().forEach(item -> {
      Price priceOfItem = priceService.getCurrentPriceForProduct(item.getProductId());
      item.setPricePerUnit(priceOfItem.getValue());
      item.setCurrency(priceOfItem.getCurrency());
    });
    return purchase;
  }

  PurchaseNotFoundException generatePurchaseNotFoundException(int purchaseId) {
    return new PurchaseNotFoundException(purchaseId);
  }
}
