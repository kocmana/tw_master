package at.technikum.masterproject.productreview.model.mapper;

import at.technikum.masterproject.productreview.model.ProductReview;
import at.technikum.masterproject.productreview.model.dto.ProductReviewDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductReviewMapper {

  ProductReviewDto productReviewToProductReviewDto(ProductReview productReview);

  ProductReview productReviewDtoToProductReview(ProductReviewDto productReviewDto);
}
