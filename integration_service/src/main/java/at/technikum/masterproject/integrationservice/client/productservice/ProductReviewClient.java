package at.technikum.masterproject.integrationservice.client.productservice;

import at.technikum.masterproject.integrationservice.model.product.ProductReview;
import at.technikum.masterproject.integrationservice.model.product.dto.CreateProductReviewInput;
import at.technikum.masterproject.integrationservice.model.product.dto.UpdateProductReviewInput;
import java.util.List;
import reactor.core.publisher.Mono;

public interface ProductReviewClient {

  Mono<List<ProductReview>> getAllProductReviews();

  Mono<List<ProductReview>> getAllProductReviewsForProduct(int productId);

  Mono<List<ProductReview>> getAllProductReviewsByCustomer(int customerId);

  Mono<Integer> saveProductReview(CreateProductReviewInput productReview);

  void updateProductReview(UpdateProductReviewInput productReview);

  void deleteProductReview(int productId);
}
