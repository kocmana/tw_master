package at.technikum.masterproject.transactionservice.recommendation.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Recommendation {

  private Long recommendationId;
  private Integer customerId;
  private Integer productId;
}
