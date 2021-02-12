package at.technikum.masterproject.transactionservice.recommendation;

import at.technikum.masterproject.transactionservice.recommendation.model.Recommendation;
import at.technikum.masterproject.transactionservice.recommendation.model.mapper.RecommendationMapper;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class RecommendationService {

  private final RecommendationRepository recommendationRepository;
  private final RecommendationMapper recommendationMapper;

  public RecommendationService(
      RecommendationRepository recommendationRepository,
      RecommendationMapper recommendationMapper) {
    this.recommendationRepository = recommendationRepository;
    this.recommendationMapper = recommendationMapper;
  }

  public Flux<Recommendation> getRecommendations() {
    return recommendationRepository.findAll()
        .map(recommendationMapper::toRecommendation);
  }
}
