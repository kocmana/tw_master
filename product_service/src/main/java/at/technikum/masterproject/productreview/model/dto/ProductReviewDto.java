package at.technikum.masterproject.productreview.model.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
@NoArgsConstructor
public class ProductReviewDto {

  private int id;
  @NotNull
  private int customerId;
  @Min(value = 0) @Max(value = 5)
  private int stars;
  @Length(max = 500)
  private String review;
}
