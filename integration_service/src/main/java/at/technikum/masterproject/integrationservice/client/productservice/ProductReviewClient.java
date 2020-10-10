package at.technikum.masterproject.integrationservice.client.productservice;

import at.technikum.masterproject.integrationservice.model.product.ProductReview;
import java.util.List;

public interface ProductReviewClient {

  List<ProductReview> getAllProductReviews();

  List<ProductReview> getAllProductReviewsForProduct(int productId);

  List<ProductReview> getAllProductReviewsByCustomer(int customerId);

  ProductReview saveProductReview(int productId, ProductReview productReview);
}
