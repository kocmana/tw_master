package at.technikum.masterproject.ecommerceservice.price.model.domain;

import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PriceId implements Serializable {

  private Integer productId;
  private LocalDateTime validFrom;
  private LocalDateTime validTo;
}
