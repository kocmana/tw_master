package at.technikum.masterproject.ecommerce.price.model.dto;

import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@NoArgsConstructor
@Getter
@Setter
public class PriceDto {

  @NotNull
  private Integer productId;
  @NotNull
  private Double value;
  @NotNull @Length(min = 1, max = 20)
  private String currency;
  @NotNull
  private String validFrom;
  @NotNull
  private String validTo;
}
