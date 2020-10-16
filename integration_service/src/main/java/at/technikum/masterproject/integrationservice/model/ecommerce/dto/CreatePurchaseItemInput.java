package at.technikum.masterproject.integrationservice.model.ecommerce.dto;

import lombok.Value;

@Value
public class CreatePurchaseItemInput {

  Integer productId;
  Integer amount;
  Float pricePerUnit;
  String currency;
}
