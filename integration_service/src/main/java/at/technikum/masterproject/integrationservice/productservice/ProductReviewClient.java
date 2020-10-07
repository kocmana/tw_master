package at.technikum.masterproject.integrationservice.productservice;

import at.technikum.masterproject.integrationservice.model.product.ProductReview;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProductReviewClient {

  Flux<ProductReview> getAllProductReviews();

  Flux<ProductReview> getAllProductReviewsForProduct(int productId);

  Flux<ProductReview> getAllProductReviewsByCustomer(int customerId);

  Mono<ProductReview> saveProductReview(int productId, ProductReview productReview);
}
