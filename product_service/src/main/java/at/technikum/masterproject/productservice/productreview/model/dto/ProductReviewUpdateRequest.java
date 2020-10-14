package at.technikum.masterproject.productservice.productreview.model.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import lombok.Value;
import org.hibernate.validator.constraints.Length;

@Value
public class ProductReviewUpdateRequest {

  @NotNull @Min(1)
  int id;
  @NotNull @Min(1)
  int customerId;
  @NotNull @Min(1)
  int productId;
  @Min(value = 0) @Max(value = 5)
  int stars;
  @Length(max = 500)
  String review;
}
