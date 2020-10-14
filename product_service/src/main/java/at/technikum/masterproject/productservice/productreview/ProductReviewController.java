package at.technikum.masterproject.productservice.productreview;

import static java.util.stream.Collectors.toUnmodifiableList;

import at.technikum.masterproject.productservice.model.ElementCreationResponse;
import at.technikum.masterproject.productservice.productinformation.ProductInformationService;
import at.technikum.masterproject.productservice.productinformation.model.Product;
import at.technikum.masterproject.productservice.productinformation.model.ProductInformationNotFoundException;
import at.technikum.masterproject.productservice.productreview.model.ProductReview;
import at.technikum.masterproject.productservice.productreview.model.dto.ProductReviewCreationRequest;
import at.technikum.masterproject.productservice.productreview.model.dto.ProductReviewResponse;
import at.technikum.masterproject.productservice.productreview.model.dto.ProductReviewUpdateRequest;
import at.technikum.masterproject.productservice.productreview.model.mapper.ProductReviewMapper;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/review")
public class ProductReviewController {

  private final ProductReviewService productReviewService;
  private final ProductInformationService productInformationService;
  private final ProductReviewMapper productReviewMapper;

  @Autowired
  public ProductReviewController(ProductReviewService productReviewService,
      ProductInformationService productInformationService,
      ProductReviewMapper productReviewMapper) {
    this.productReviewService = productReviewService;
    this.productInformationService = productInformationService;
    this.productReviewMapper = productReviewMapper;
  }

  @GetMapping
  public ResponseEntity<List<ProductReviewResponse>> getAllReviews(Pageable pageable) {
    List<ProductReview> productReviews = productReviewService.getAllReviews(pageable);
    return toProductReviewResponse(productReviews);
  }

  @DeleteMapping("/{id}")
  public void deleteReview(@PathVariable @NotNull Integer id) {
    productReviewService.deleteReviewById(id);
  }

  @GetMapping("/product/{productId}")
  public ResponseEntity<List<ProductReviewResponse>> getReviewsForProduct(
      @PathVariable @Valid @NotNull int productId) {
    if (productInformationService.productDoesNotExist(productId)) {
      throw new ProductInformationNotFoundException(productId);
    }
    productInformationService.retrieveProductById(productId);
    List<ProductReview> productReviews = productReviewService.getReviewsForProduct(productId);
    return toProductReviewResponse(productReviews);
  }

  @GetMapping("/customer/{customerId}")
  public ResponseEntity<List<ProductReviewResponse>> getReviewsForCustomer(
      @PathVariable @Valid @NotNull int customerId) {
    List<ProductReview> productReviews = productReviewService.getReviewsForCustomer(customerId);
    return toProductReviewResponse(productReviews);
  }

  @PostMapping("/product/{productId}")
  public ResponseEntity<ElementCreationResponse> postReview(
      @PathVariable @Valid @NotNull Integer productId,
      @RequestBody @Valid ProductReviewCreationRequest productReviewCreationRequest) {
    ProductReview productReview = productReviewMapper
        .productReviewCreationRequestToProductReview(productReviewCreationRequest);
    Product reviewedProduct = productInformationService.retrieveProductById(productId);
    productReview.setProduct(reviewedProduct);
    Integer reviewId = productReviewService.saveReview(productReview);
    return ResponseEntity.ok(new ElementCreationResponse(reviewId));
  }

  @PutMapping
  public void updateReview(
      @RequestBody @Valid ProductReviewUpdateRequest productReviewUpdateRequest) {
    ProductReview productReview = productReviewMapper
        .productReviewUpdateRequestToProductReview(productReviewUpdateRequest);
    productReviewService.updateReview(productReview);
  }

  private ResponseEntity<List<ProductReviewResponse>> toProductReviewResponse(
      List<ProductReview> productReviews) {
    List<ProductReviewResponse> productReviewResponses = productReviews.stream()
        .map(productReviewMapper::productReviewToProductReviewResponse)
        .collect(toUnmodifiableList());
    return ResponseEntity.ok(productReviewResponses);
  }

}
