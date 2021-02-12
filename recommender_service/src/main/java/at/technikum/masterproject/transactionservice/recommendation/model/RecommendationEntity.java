package at.technikum.masterproject.transactionservice.recommendation.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;

@Data
@Builder
public class RecommendationEntity {

  @Id
  private Long recommendationId;
  @Column("customer_id")
  private Integer customerId;
  @Column("product_id")
  private Integer productId;
}
