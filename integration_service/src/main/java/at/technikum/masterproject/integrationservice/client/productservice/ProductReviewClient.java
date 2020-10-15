package at.technikum.masterproject.integrationservice.client.productservice;

import at.technikum.masterproject.integrationservice.model.product.ProductReview;
import at.technikum.masterproject.integrationservice.model.product.dto.CreateProductReviewInput;
import at.technikum.masterproject.integrationservice.model.product.dto.UpdateProductReviewInput;
import java.util.List;

public interface ProductReviewClient {

  List<ProductReview> getAllProductReviews();

  List<ProductReview> getAllProductReviewsForProduct(int productId);

  List<ProductReview> getAllProductReviewsByCustomer(int customerId);

  int saveProductReview(CreateProductReviewInput productReview);

  void updateProductReview(UpdateProductReviewInput productReview);

  void deleteProductReview(int productId);
}
