package at.technikum.masterproject.integrationservice.model.ecommerce;

import lombok.Value;

@Value
public class PurchaseItem {

  Long id;
  Integer purchaseId;
  Integer productId;
  Integer amount;
  Float pricePerUnit;
  String currency;
}
