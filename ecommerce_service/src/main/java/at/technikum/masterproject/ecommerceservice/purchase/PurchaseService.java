package at.technikum.masterproject.ecommerceservice.purchase;

import at.technikum.masterproject.ecommerceservice.price.PriceService;
import at.technikum.masterproject.ecommerceservice.price.model.Price;
import at.technikum.masterproject.ecommerceservice.purchase.model.Purchase;
import at.technikum.masterproject.ecommerceservice.purchase.model.PurchaseNotFoundException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PurchaseService {

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

  public List<Purchase> getPurchasesForCustomer(int customerId) {
    return purchaseRepository.findPurchasesByCustomerId(customerId);
  }

  public Purchase getPurchaseById(int purchaseId) {
    return purchaseRepository.findById(purchaseId)
        .orElseThrow(() -> generatePurchaseNotFoundException(purchaseId));
  }

  public int savePurchase(Purchase purchase) {
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

  PurchaseNotFoundException generatePurchaseNotFoundException(int purchaseId) {
    return new PurchaseNotFoundException(purchaseId);
  }
}
