package at.technikum.masterproject.integrationservice.client.productservice;

import at.technikum.masterproject.integrationservice.model.product.ProductReview;
import at.technikum.masterproject.integrationservice.model.product.dto.CreateProductReviewInput;
import at.technikum.masterproject.integrationservice.model.product.dto.UpdateProductReviewInput;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class ProductReviewClientImpl implements ProductReviewClient {

  private static final String REVIEW_ENDPOINT = "review";

  private final WebClient webClient;

  @Autowired
  public ProductReviewClientImpl(@Qualifier("productServiceWebClient") WebClient webClient) {
    this.webClient = webClient;
  }

  @Override
  public Mono<List<ProductReview>> getAllProductReviews() {
    return webClient.get()
        .uri(uriBuilder -> uriBuilder
            .pathSegment(REVIEW_ENDPOINT)
            .build())
        .retrieve()
        .bodyToFlux(ProductReview.class)
        .retry(2)
        .collectList();
  }

  @Override
  public Mono<List<ProductReview>> getAllProductReviewsForProduct(int productId) {
    return webClient.get()
        .uri(uriBuilder -> uriBuilder
            .pathSegment(REVIEW_ENDPOINT)
            .pathSegment("product")
            .pathSegment("{productId}")
            .build(productId))
        .retrieve()
        .bodyToFlux(ProductReview.class)
        .retry(2)
        .collectList();
  }

  @Override
  public Mono<List<ProductReview>> getAllProductReviewsByCustomer(int customerId) {
    return webClient.get()
        .uri(uriBuilder -> uriBuilder
            .pathSegment(REVIEW_ENDPOINT)
            .pathSegment("customer")
            .pathSegment("{customerId}")
            .build(customerId))
        .retrieve()
        .bodyToFlux(ProductReview.class)
        .retry(2)
        .collectList();
  }

  @Override
  public Mono<Integer> saveProductReview(CreateProductReviewInput productReview) {
    return webClient.post()
        .uri(uriBuilder -> uriBuilder
        .pathSegment(REVIEW_ENDPOINT)
        .build())
        .bodyValue(productReview)
        .retrieve()
        .bodyToMono(ProductReview.class)
        .retry(2)
        .map(ProductReview::getId);
  }

  @Override
  public void updateProductReview(UpdateProductReviewInput productReview) {
    webClient.put()
        .uri(uriBuilder -> uriBuilder
            .pathSegment(REVIEW_ENDPOINT)
        .build())
        .bodyValue(productReview)
        .retrieve()
        .toBodilessEntity()
        .retry(2);
  }

  @Override
  public void deleteProductReview(int productId) {
    webClient.delete()
        .uri(uriBuilder -> uriBuilder
        .pathSegment(REVIEW_ENDPOINT)
        .pathSegment("{productId}")
            .build(productId))
        .retrieve()
        .toBodilessEntity()
        .retry(2);
  }
}
