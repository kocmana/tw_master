package at.technikum.masterproject.productservice.productreview.model.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import lombok.Value;
import org.hibernate.validator.constraints.Length;

@Value
public class ProductReviewCreationRequest {

  @NotNull
  int customerId;
  @Min(value = 0) @Max(value = 5)
  int stars;
  @Length(max = 500)
  String review;
}
