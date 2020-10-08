package at.technikum.masterproject.integrationservice.model.ecommerce;

import lombok.Value;

@Value
public class Price {

  Integer productId;
  Float value;
  String currency;
  String validFrom;
  String validTo;
}
