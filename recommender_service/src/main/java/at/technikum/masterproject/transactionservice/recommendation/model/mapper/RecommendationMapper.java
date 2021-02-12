package at.technikum.masterproject.transactionservice.recommendation.model.mapper;

import at.technikum.masterproject.transactionservice.recommendation.model.Recommendation;
import at.technikum.masterproject.transactionservice.recommendation.model.RecommendationDto;
import at.technikum.masterproject.transactionservice.recommendation.model.RecommendationEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RecommendationMapper {

  Recommendation toRecommendation(RecommendationEntity recommendationEntity);

  RecommendationDto toRecommendationDto(Recommendation recommendation);
}
