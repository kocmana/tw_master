package at.technikum.masterproject.ecommerceservice.price.model.domain;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "price")
@IdClass(PriceId.class)
@Data
@NoArgsConstructor
public class Price {

  @Id
  @Column(name = "product_id")
  private Integer productId;
  private Float value;
  private String currency;
  @Id
  @Column(name = "valid_from")
  private LocalDateTime validFrom;
  @Id
  @Column(name = "valid_to")
  private LocalDateTime validTo;
}
