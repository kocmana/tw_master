package at.technikum.masterproject.productservice.productreview.model.mapper;

import at.technikum.masterproject.productservice.productreview.model.domain.ProductReview;
import at.technikum.masterproject.productservice.productreview.model.dto.ProductReviewCreationRequest;
import at.technikum.masterproject.productservice.productreview.model.dto.ProductReviewResponse;
import at.technikum.masterproject.productservice.productreview.model.dto.ProductReviewUpdateRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductReviewMapper {

  @Mapping(source = "product.id", target = "productId")
  ProductReviewResponse productReviewToProductReviewResponse(ProductReview productReview);

  ProductReview productReviewCreationRequestToProductReview(ProductReviewCreationRequest productReviewCreationRequest);

  ProductReview productReviewUpdateRequestToProductReview(ProductReviewUpdateRequest productReviewUpdateRequest);
}
