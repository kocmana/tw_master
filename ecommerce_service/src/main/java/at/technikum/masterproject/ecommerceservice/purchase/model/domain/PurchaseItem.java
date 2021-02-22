package at.technikum.masterproject.ecommerceservice.purchase.model.domain;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "purchase_item")
@Data
public class PurchaseItem implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;
  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "purchase_id")
  private Purchase purchase;
  @Column(name = "product_id")
  private Integer productId;
  private Integer amount;
  @Column(name = "price_per_unit")
  private Float pricePerUnit;
  @Column(name = "currency")
  private String currency;
}
