package at.technikum.masterproject.order.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "order_item")
@Data
public class OrderItem {

  @Column(name = "order_id")
  @ManyToOne(cascade = CascadeType.ALL)
  private Order order;
  @Column(name = "product_id")
  private Integer productId;
  private Integer amount;
  @Column(name = "price_perUnit")
  private Integer pricePerUnit;
}
