package at.technikum.masterproject.transactionservice.recommendation;

import at.technikum.masterproject.transactionservice.recommendation.model.RecommendationEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecommendationRepository extends ReactiveCrudRepository<RecommendationEntity, Long> {
}
