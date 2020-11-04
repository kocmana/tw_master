package at.technikum.masterproject.integrationservice.client.ecommerceservice;

import at.technikum.masterproject.integrationservice.model.ecommerce.Price;
import at.technikum.masterproject.integrationservice.model.ecommerce.dto.CreatePriceInput;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@Slf4j
public class PriceClientImpl implements PriceClient {

  private static final String PRICE_ENDPOINT = "/price";

  private final WebClient webClient;

  @Autowired
  public PriceClientImpl(@Qualifier("ecommerceServiceWebClient") WebClient webClient) {
    this.webClient = webClient;
  }

  @Override
  public Price getCurrentPriceForProduct(int productId) {
    return webClient.get()
        .uri(uriBuilder -> uriBuilder
            .path(PRICE_ENDPOINT)
            .pathSegment("product")
            .pathSegment("{productId}")
            .build(productId))
        .retrieve()
        .bodyToMono(Price.class)
        .retry(2)
        .block();
  }

  @Override
  public List<Price> getAllPricesForProduct(int productId) {
    return webClient.get()
        .uri(uriBuilder -> uriBuilder
            .path(PRICE_ENDPOINT)
            .pathSegment("product")
            .pathSegment("{productId}")
            .pathSegment("all")
            .build(productId))
        .retrieve()
        .bodyToFlux(Price.class)
        .retry(2)
        .collectList()
        .block();
  }

  @Override
  public Price savePrice(CreatePriceInput price) {
    return webClient.post()
        .uri(uriBuilder -> uriBuilder
            .path(PRICE_ENDPOINT)
            .build())
        .bodyValue(price)
        .retrieve()
        .bodyToMono(Price.class)
        .block();
  }

}
