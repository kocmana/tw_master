package at.technikum.masterproject.integrationservice.client.productservice;

import at.technikum.masterproject.integrationservice.model.product.ProductReview;
import java.util.function.Consumer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class ProductReviewClientImpl implements ProductReviewClient {

  private static final String REVIEW_ENDPOINT = "review";
  private static final String REVIEW_BY_PRODUCT_ENDPOINT = "review/product";
  private static final String REVIEW_BY_CUSTOMER_ENDPOINT = "review/customer";

  private final WebClient webClient;
  private final Consumer<? super Throwable> handleError = exception -> log.error("Product call failed: {}",
      exception.getMessage());

  @Autowired
  public ProductReviewClientImpl(@Qualifier("productServiceWebClient") WebClient webClient) {
    this.webClient = webClient;
  }

  @Override
  public Flux<ProductReview> getAllProductReviews() {
    return webClient.get()
        .uri(uriBuilder -> uriBuilder
            .path(REVIEW_ENDPOINT)
            .build())
        .retrieve()
        .bodyToFlux(ProductReview.class)
        .retry(3)
        .doOnError(handleError);
  }

  @Override
  public Flux<ProductReview> getAllProductReviewsForProduct(int productId) {
    return webClient.get()
        .uri(uriBuilder -> uriBuilder
            .path(REVIEW_BY_PRODUCT_ENDPOINT)
            .pathSegment("{id}")
            .build(productId))
        .retrieve()
        .bodyToFlux(ProductReview.class)
        .retry(3)
        .doOnError(handleError);
  }

  @Override
  public Flux<ProductReview> getAllProductReviewsByCustomer(int customerId) {
    return webClient.get()
        .uri(uriBuilder -> uriBuilder
            .path(REVIEW_BY_CUSTOMER_ENDPOINT)
            .pathSegment("{id}")
            .build(customerId))
        .retrieve()
        .bodyToFlux(ProductReview.class)
        .doOnError(handleError);
  }

  @Override
  public Mono<ProductReview> saveProductReview(int productId, ProductReview productReview) {
    return webClient.post()
        .uri(uriBuilder -> uriBuilder
            .path(REVIEW_BY_PRODUCT_ENDPOINT)
            .pathSegment("{id}")
            .build(productId))
        .bodyValue(productReview)
        .retrieve()
        .bodyToMono(ProductReview.class)
        .doOnError(handleError);
  }

}
