package at.technikum.masterproject.productservice.productreview;

import at.technikum.masterproject.productservice.productreview.model.ProductReview;
import at.technikum.masterproject.productservice.productreview.model.ProductReviewNotFoundException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ProductReviewService {

  private final ProductReviewRepository productReviewRepository;

  @Autowired
  public ProductReviewService(ProductReviewRepository productReviewRepository) {
    this.productReviewRepository = productReviewRepository;
  }

  public List<ProductReview> getAllReviews(Pageable pageable) {
    return productReviewRepository.findAll(pageable).getContent();
  }

  public ProductReview getReviewById(int reviewId) {
    return productReviewRepository.findById(reviewId)
        .orElseThrow(() -> generateReviewNotFoundException(reviewId));
  }

  public List<ProductReview> getReviewsForProduct(int productId) {
    return productReviewRepository.findByProductId(productId);
  }

  public List<ProductReview> getReviewsForCustomer(int customerId) {
    return productReviewRepository.findByCustomerId(customerId);
  }

  public int saveReview(ProductReview productReview) {
    ProductReview savedReview = productReviewRepository.save(productReview);
    return savedReview.getId();
  }

  public void updateReview(ProductReview productReview) {
    ProductReview oldProductReview = getReviewById(productReview.getId());
    productReview.setProduct(oldProductReview.getProduct());
    productReviewRepository.save(productReview);
  }

  public void deleteReviewById(Integer id) {
    if (!productReviewRepository.existsById(id)) {
      throw generateReviewNotFoundException(id);
    }
    productReviewRepository.deleteById(id);
  }

  private ProductReviewNotFoundException generateReviewNotFoundException(int reviewId) {
    return new ProductReviewNotFoundException(reviewId);
  }
}
