package at.technikum.masterproject.ecommerce.purchase.model.dto;

import at.technikum.masterproject.ecommerce.purchase.model.Purchase;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class PurchaseItemDto {

  private Purchase purchase;
  private Integer productId;
  private Integer amount;
  private Integer pricePerUnit;
}

