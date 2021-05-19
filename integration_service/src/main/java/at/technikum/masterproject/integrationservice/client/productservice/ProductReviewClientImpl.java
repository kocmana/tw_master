package at.technikum.masterproject.integrationservice.client.productservice;

import at.technikum.masterproject.integrationservice.model.product.ProductReview;
import at.technikum.masterproject.integrationservice.model.product.ProductServiceException;
import at.technikum.masterproject.integrationservice.model.product.dto.CreateProductReviewInput;
import at.technikum.masterproject.integrationservice.model.product.dto.ElementCreationResponse;
import at.technikum.masterproject.integrationservice.model.product.dto.UpdateProductReviewInput;
import java.net.URI;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
  private static final String REVIEW_BY_ID_ENDPOINT = "/review/{productId}";
  private static final String REVIEW_BY_PRODUCT_ENDPOINT = "/review/product/{productId}";
  private static final String REVIEW_BY_CUSTOMER_ENDPOINT = "/review/customer/{customerId}";

  private final RestTemplate restTemplate;

  @Autowired
  public ProductReviewClientImpl(@ProductService RestTemplate restTemplate) {
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
  public int saveProductReview(CreateProductReviewInput productReview) {
    ElementCreationResponse response = restTemplate.postForObject(
        REVIEW_BY_PRODUCT_ENDPOINT,
        productReview,
        ElementCreationResponse.class,
        productReview.getProductId()
    );
    return Optional.ofNullable(response)
        .orElseThrow(() -> new ProductServiceException("Could not retrieve element id from response."))
        .getId();
  }

  @Override
  public void updateProductReview(UpdateProductReviewInput productReview) {
    restTemplate.put(
        URI.create(REVIEW_ENDPOINT),
        productReview
    );
  }

  @Override
  public void deleteProductReview(int productId) {
    restTemplate.delete(
        REVIEW_BY_ID_ENDPOINT,
        productId
    );
  }
}
