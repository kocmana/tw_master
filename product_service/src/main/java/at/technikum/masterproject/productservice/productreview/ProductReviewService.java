package at.technikum.masterproject.productservice.productreview;

import at.technikum.masterproject.productservice.productreview.model.ProductReview;
import at.technikum.masterproject.productservice.productreview.model.ProductReviewNotFoundException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
class ProductReviewService {

  private final ProductReviewRepository productReviewRepository;

  @Autowired
  public ProductReviewService(ProductReviewRepository productReviewRepository) {
    this.productReviewRepository = productReviewRepository;
  }

  List<ProductReview> getAllReviews(Pageable pageable) {
    return productReviewRepository.findAll(pageable).getContent();
  }

  ProductReview getReviewById(int reviewId) {
    return productReviewRepository.findById(reviewId)
        .orElseThrow(() -> generateReviewNotFoundException(reviewId));
  }

  List<ProductReview> getReviewsForProduct(int productId) {
    return productReviewRepository.findByProductId(productId);
  }

  List<ProductReview> getReviewsForCustomer(int customerId) {
    return productReviewRepository.findByCustomerId(customerId);
  }

  int saveReview(ProductReview productReview) {
    ProductReview savedReview = productReviewRepository.save(productReview);
    return savedReview.getId();
  }

  void updateReview(ProductReview productReview) {
    ProductReview oldProductReview = getReviewById(productReview.getId());
    productReview.setProduct(oldProductReview.getProduct());
    productReviewRepository.save(productReview);
  }

  void deleteReviewById(Integer id) {
    if (!productReviewRepository.existsById(id)) {
      throw generateReviewNotFoundException(id);
    }
    productReviewRepository.deleteById(id);
  }

  private ProductReviewNotFoundException generateReviewNotFoundException(int reviewId) {
    return new ProductReviewNotFoundException(reviewId);
  }
}
