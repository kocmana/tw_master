package at.technikum.masterproject.ecommerce.purchase;

import at.technikum.masterproject.ecommerce.price.PriceService;
import at.technikum.masterproject.ecommerce.price.model.Price;
import at.technikum.masterproject.ecommerce.purchase.model.Purchase;
import at.technikum.masterproject.ecommerce.purchase.model.PurchaseNotFoundException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
class PurchaseService {

  private final PurchaseRepository purchaseRepository;
  private final PurchaseItemRepository purchaseItemRepository;
  private final PriceService priceService;

  @Autowired
  PurchaseService(PurchaseRepository purchaseRepository, PurchaseItemRepository purchaseItemRepository,
      PriceService priceService) {
    this.purchaseRepository = purchaseRepository;
    this.purchaseItemRepository = purchaseItemRepository;
    this.priceService = priceService;
  }

  List<Purchase> getPurchasesForCustomer(int customerId) {
    return purchaseRepository.findPurchasesByCustomerId(customerId);
  }

  Purchase getPurchaseById(long purchaseId) {
    return purchaseRepository.findById(purchaseId)
        .orElseThrow(() -> generatePurchaseNotFoundException(purchaseId));
  }

  Long savePurchase(Purchase purchase) {
    Purchase updatedPurchaseInformation = updatePurchaseItemInformation(purchase);
    purchaseItemRepository.saveAll(updatedPurchaseInformation.getItems());
    Purchase savedPurchase = purchaseRepository.save(updatedPurchaseInformation);
    return savedPurchase.getId();
  }

  //not pure!
  private Purchase updatePurchaseItemInformation(Purchase purchase) {
    purchase.getItems().forEach(item -> {
      item.setPurchase(purchase);
      Price priceOfItem = priceService.getCurrentPriceForProduct(item.getProductId());
      item.setPricePerUnit(priceOfItem.getValue());
      item.setCurrency(priceOfItem.getCurrency());
    });
    return purchase;
  }

  PurchaseNotFoundException generatePurchaseNotFoundException(long purchaseId) {
    return new PurchaseNotFoundException(purchaseId);
  }
}
