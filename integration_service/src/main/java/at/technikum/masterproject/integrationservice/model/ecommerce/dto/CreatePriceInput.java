package at.technikum.masterproject.integrationservice.model.ecommerce.dto;

import lombok.Value;

@Value
public class CreatePriceInput {

  Integer productId;
  Float value;
  String currency;
  String validFrom;
  String validTo;
}
