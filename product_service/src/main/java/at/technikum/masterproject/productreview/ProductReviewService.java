package at.technikum.masterproject.productreview;

import at.technikum.masterproject.productreview.model.ProductReview;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
class ProductReviewService {

  private final ProductReviewRepository productReviewRepository;

  @Autowired
  public ProductReviewService(ProductReviewRepository productReviewRepository) {
    this.productReviewRepository = productReviewRepository;
  }

  List<ProductReview> getReviewsForProduct(int productId) {
    return productReviewRepository.findByProductId(productId);
  }

  List<ProductReview> getReviewsForCustomer(int customerId) {
    return productReviewRepository.findByCustomerId(customerId);
  }

  ProductReview saveReview(ProductReview productReview) {
    return productReviewRepository.save(productReview);
  }

}
