package at.technikum.masterproject.integrationservice.model.product.dto;

import lombok.Value;

@Value
public class CreateProductReviewInput {
  Integer productId;
  Integer customerId;
  Integer stars;
  String review;
}
