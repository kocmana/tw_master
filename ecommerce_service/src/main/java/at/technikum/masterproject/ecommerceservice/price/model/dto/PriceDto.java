package at.technikum.masterproject.ecommerceservice.price.model.dto;

import javax.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Value;
import org.hibernate.validator.constraints.Length;

@Value
@Builder
public class PriceDto {

  @NotNull
  Integer productId;
  @NotNull
  Float value;
  @NotNull @Length(min = 1, max = 20)
  String currency;
  @NotNull
  String validFrom;
  @NotNull
  String validTo;
}
