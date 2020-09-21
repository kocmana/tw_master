package at.technikum.masterproject.purchase.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "purchase_item")
@Data
@IdClass(PurchaseItemId.class)
public class PurchaseItem {

  @Id
  @ManyToOne
  private Purchase purchase;
  @Id
  @Column(name = "product_id")
  private Integer productId;
  private Integer amount;
  @Column(name = "price_per_unit")
  private Integer pricePerUnit;
}
