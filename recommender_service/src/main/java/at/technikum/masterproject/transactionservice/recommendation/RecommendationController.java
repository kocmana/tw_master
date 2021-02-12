package at.technikum.masterproject.transactionservice.recommendation;

import at.technikum.masterproject.transactionservice.recommendation.model.RecommendationDto;
import at.technikum.masterproject.transactionservice.recommendation.model.mapper.RecommendationMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/recommendation")
public class RecommendationController {

  private final RecommendationService recommendationService;
  private final RecommendationMapper recommendationMapper;

  public RecommendationController(RecommendationService recommendationService,
                                  RecommendationMapper recommendationMapper) {
    this.recommendationService = recommendationService;
    this.recommendationMapper = recommendationMapper;
  }

  @GetMapping
  public Flux<RecommendationDto> getRecommendations() {
    return recommendationService.getRecommendations()
        .map(recommendationMapper::toRecommendationDto);
  }

}
