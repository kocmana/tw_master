package at.technikum.masterproject.productservice.productreview.model.mapper;

import at.technikum.masterproject.productservice.productreview.model.ProductReview;
import at.technikum.masterproject.productservice.productreview.model.dto.ProductReviewDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductReviewMapper {

  @Mapping(source = "product.id", target = "productId")
  ProductReviewDto productReviewToProductReviewDto(ProductReview productReview);

  ProductReview productReviewDtoToProductReview(ProductReviewDto productReviewDto);
}
