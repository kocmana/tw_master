package at.technikum.masterproject.integrationservice.client.productservice;

import at.technikum.masterproject.integrationservice.model.product.ProductReview;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class ProductReviewClientImpl implements ProductReviewClient {

  private static final String REVIEW_ENDPOINT = "/review";
  private static final String REVIEW_BY_PRODUCT_ENDPOINT = "/review/product/{productId}";
  private static final String REVIEW_BY_CUSTOMER_ENDPOINT = "/review/customer/{customerId}";

  private final RestTemplate restTemplate;

  @Autowired
  public ProductReviewClientImpl(
      @Qualifier("productServiceRestTemplate") RestTemplate restTemplate) {
    this.restTemplate = restTemplate;
  }

  @Override
  public List<ProductReview> getAllProductReviews() {
    ResponseEntity<List<ProductReview>> response = restTemplate.exchange(
        REVIEW_ENDPOINT,
        HttpMethod.GET,
        HttpEntity.EMPTY,
        new ParameterizedTypeReference<List<ProductReview>>() {
        }
    );

    return Optional.ofNullable(response.getBody())
        .orElse(Collections.emptyList());
  }

  @Override
  public List<ProductReview> getAllProductReviewsForProduct(int productId) {
    ResponseEntity<List<ProductReview>> response = restTemplate.exchange(
        REVIEW_BY_PRODUCT_ENDPOINT,
        HttpMethod.GET,
        HttpEntity.EMPTY,
        new ParameterizedTypeReference<List<ProductReview>>() {
        },
        productId
    );

    return Optional.ofNullable(response.getBody())
        .orElse(Collections.emptyList());
  }

  @Override
  public List<ProductReview> getAllProductReviewsByCustomer(int customerId) {
    ResponseEntity<List<ProductReview>> response = restTemplate.exchange(
        REVIEW_BY_CUSTOMER_ENDPOINT,
        HttpMethod.GET,
        HttpEntity.EMPTY,
        new ParameterizedTypeReference<List<ProductReview>>() {
        },
        customerId
    );

    return Optional.ofNullable(response.getBody())
        .orElse(Collections.emptyList());
  }

  @Override
  public ProductReview saveProductReview(int productId, ProductReview productReview) {
    return restTemplate.postForObject(
        REVIEW_BY_PRODUCT_ENDPOINT,
        productReview,
        ProductReview.class,
        productId
    );
  }

}
