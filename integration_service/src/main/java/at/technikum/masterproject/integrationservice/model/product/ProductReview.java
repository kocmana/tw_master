package at.technikum.masterproject.integrationservice.model.product;

import lombok.Value;

@Value
public class ProductReview {

  int id;
  int customerId;
  int productId;
  int stars;
  String review;
}
