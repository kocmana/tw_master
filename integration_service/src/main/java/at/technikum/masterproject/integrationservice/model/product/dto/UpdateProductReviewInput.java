package at.technikum.masterproject.integrationservice.model.product.dto;

import lombok.Value;

@Value
public class UpdateProductReviewInput {
  Integer id;
  Integer productId;
  Integer customerId;
  Integer stars;
  String review;
}
