package at.technikum.masterproject.integrationservice.client.ecommerceservice;

import at.technikum.masterproject.integrationservice.model.ecommerce.EcommerceServiceException;
import at.technikum.masterproject.integrationservice.model.ecommerce.Price;
import at.technikum.masterproject.integrationservice.model.ecommerce.dto.CreatePriceInput;
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
public class PriceClientImpl implements PriceClient {

  private static final String PRICE_ENDPOINT = "/price";
  private static final String PRICE_BY_PRODUCT_ENDPOINT = "/price/product/{productId}";
  private static final String ALL_PRICES_BY_PRODUCT_ENDPOINT = "/price/product/{productId}/all";

  private final RestTemplate restTemplate;

  @Autowired
  public PriceClientImpl(@Qualifier("ecommerceServiceRestTemplate")RestTemplate restTemplate) {
    this.restTemplate = restTemplate;
  }

  @Override
  public Price getCurrentPriceForProduct(int productId) {
    return restTemplate.getForObject(
        PRICE_BY_PRODUCT_ENDPOINT,
        Price.class,
        productId
    );
  }

  @Override
  public List<Price> getAllPricesForProduct(int productId) {
    ResponseEntity<List<Price>> response = restTemplate.exchange(
        ALL_PRICES_BY_PRODUCT_ENDPOINT,
        HttpMethod.GET,
        HttpEntity.EMPTY,
        new ParameterizedTypeReference<List<Price>>() {
        },
        productId
    );

    return Optional.ofNullable(response.getBody())
        .orElse(Collections.emptyList());
  }

  @Override
  public Price savePrice(CreatePriceInput price) {
    Price response = restTemplate.postForObject(
        PRICE_ENDPOINT,
        price,
        Price.class
    );
    return Optional.ofNullable(response)
        .orElseThrow(() -> new EcommerceServiceException("Could not retrieve element id from response."));
  }

}
