package at.technikum.masterproject.integrationservice.client.productservice;

import static org.springframework.http.HttpMethod.GET;

import at.technikum.masterproject.integrationservice.model.product.Product;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class ProductInformationClientImpl implements ProductInformationClient {

  private static final String PRODUCT_ENDPOINT = "/product";
  private static final String PRODUCT_BY_ID_ENDPOINT = "/product/{productId}";

  private final RestTemplate restTemplate;

  @Autowired
  public ProductInformationClientImpl(
      @Qualifier("productServiceRestTemplate") RestTemplate restTemplate) {
    this.restTemplate = restTemplate;
  }

  @Override
  public Product getProductById(int productId) {
    return restTemplate.getForObject(
        PRODUCT_BY_ID_ENDPOINT,
        Product.class,
        productId
    );
  }

  @Override
  public List<Product> getAllProducts() {
    ResponseEntity<List<Product>> response = restTemplate.exchange(
        PRODUCT_ENDPOINT,
        GET,
        HttpEntity.EMPTY,
        new ParameterizedTypeReference<List<Product>>() {
        }
    );

    return Optional.ofNullable(response.getBody())
        .orElse(Collections.emptyList());
  }

  @Override
  public Product saveProduct(Product product) {
    return restTemplate.postForObject(
        PRODUCT_ENDPOINT,
        product,
        Product.class
    );
  }

  @Override
  public Product updateProduct(Product product) {
    return restTemplate.patchForObject(
        PRODUCT_ENDPOINT,
        product,
        Product.class
    );
  }

}
