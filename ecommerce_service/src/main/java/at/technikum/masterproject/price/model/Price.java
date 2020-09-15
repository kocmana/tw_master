package at.technikum.masterproject.price.model;

import java.time.LocalDateTime;
import java.util.Currency;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "price")
@IdClass(PriceId.class)
@Data
public class Price {

  @Id
  @Column(name = "product_id")
  private Integer productId;
  private Double value;
  private Currency currency;
  @Id
  @Column(name = "valid_from")
  private LocalDateTime validFrom;
  @Id
  @Column(name = "valid_to")
  private LocalDateTime validTo;
}
