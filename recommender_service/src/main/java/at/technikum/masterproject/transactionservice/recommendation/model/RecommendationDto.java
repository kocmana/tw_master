package at.technikum.masterproject.transactionservice.recommendation.model;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class RecommendationDto {

  Long recommendationId;
  Integer customerId;
  Integer productId;
}
