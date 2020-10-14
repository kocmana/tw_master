package at.technikum.masterproject.productservice.productreview.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ProductReviewResponse {

  private int id;
  private int customerId;
  private int productId;
  private int stars;
  private String review;
}
