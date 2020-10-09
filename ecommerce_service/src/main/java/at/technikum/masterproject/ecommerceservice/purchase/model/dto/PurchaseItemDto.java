package at.technikum.masterproject.ecommerceservice.purchase.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class PurchaseItemDto {

  private Long id;
  private Integer purchaseId;
  private Integer productId;
  private Integer amount;
  private Float pricePerUnit;
  private String currency;
}

