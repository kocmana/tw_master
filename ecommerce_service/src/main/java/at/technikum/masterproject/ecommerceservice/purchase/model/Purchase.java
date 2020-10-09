package at.technikum.masterproject.ecommerceservice.purchase.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "purchase")
@Data
public class Purchase implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column(name = "customer_id")
  private Integer customerId;
  @OneToMany(mappedBy = "purchase")
  private List<PurchaseItem> items;
  @Column(name = "payment_type")
  @Enumerated(value = EnumType.STRING)
  private PaymentType paymentType;
}
