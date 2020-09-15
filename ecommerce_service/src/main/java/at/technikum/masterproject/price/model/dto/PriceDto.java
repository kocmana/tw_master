package at.technikum.masterproject.price.model.dto;

import java.time.LocalDateTime;
import java.util.Currency;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class PriceDto {

  private Integer productId;
  private Double value;
  private Currency currency;
  private LocalDateTime validFrom;
  private LocalDateTime validTo;
}
