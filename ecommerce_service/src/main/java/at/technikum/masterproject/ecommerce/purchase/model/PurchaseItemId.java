package at.technikum.masterproject.ecommerce.purchase.model;

import java.io.Serializable;
import lombok.Data;

@Data
public class PurchaseItemId implements Serializable {

  private Purchase purchase;
  private Integer productId;
}
