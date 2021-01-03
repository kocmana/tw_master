package at.technikum.masterproject.monolithicservice.resolver.mutation;

import at.technikum.masterproject.productservice.productinformation.ProductInformationService;
import at.technikum.masterproject.productservice.productinformation.model.Product;
import at.technikum.masterproject.productservice.productinformation.model.dto.ProductCreationRequest;
import at.technikum.masterproject.productservice.productinformation.model.dto.ProductUpdateRequest;
import at.technikum.masterproject.productservice.productinformation.model.mapper.ProductMapper;
import at.technikum.masterproject.productservice.productreview.ProductReviewService;
import at.technikum.masterproject.productservice.productreview.model.ProductReview;
import at.technikum.masterproject.productservice.productreview.model.dto.ProductReviewCreationRequest;
import at.technikum.masterproject.productservice.productreview.model.dto.ProductReviewUpdateRequest;
import at.technikum.masterproject.productservice.productreview.model.mapper.ProductReviewMapper;
import graphql.kickstart.tools.GraphQLMutationResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProductMutationResolver implements GraphQLMutationResolver {

  private final ProductMapper productMapper;
  private final ProductInformationService productInformationService;
  private final ProductReviewMapper productReviewMapper;
  private final ProductReviewService productReviewService;

  public int createProduct(ProductCreationRequest productDto) {
    Product product = productMapper.productCreationRequestToProduct(productDto);
    return productInformationService.saveNewProduct(product);
  }

  public void updateProduct(ProductUpdateRequest productDto) {
    Product product = productMapper.productUpdateRequestToProduct(productDto);
    productInformationService.updateProduct(product);
  }

  public void deleteProduct(int productId) {
    productInformationService.deleteProductById(productId);
  }

  public int createProductReview(ProductReviewCreationRequest productReviewDto) {
    ProductReview productReview = productReviewMapper.productReviewCreationRequestToProductReview(productReviewDto);
    return productReviewService.saveReview(productReview);
  }

  public void updateProductReview(ProductReviewUpdateRequest productReviewDto) {
    ProductReview productReview = productReviewMapper.productReviewUpdateRequestToProductReview(productReviewDto);
    productReviewService.updateReview(productReview);
  }

  public void deleteProductReview(int productId) {
    productReviewService.deleteReviewById(productId);
  }

}
