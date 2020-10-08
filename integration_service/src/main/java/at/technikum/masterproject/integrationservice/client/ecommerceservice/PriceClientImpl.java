package at.technikum.masterproject.integrationservice.client.ecommerceservice;

import at.technikum.masterproject.integrationservice.model.ecommerce.Price;
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
public class PriceClientImpl implements PriceClient {

  private static final String PRICE_ENDPOINT = "price";
  private static final String PRICE_BY_PRODUCT_ENDPOINT = "price/product";

  private final WebClient webClient;
  private final Consumer<? super Throwable> handleError = exception -> log
      .info("eCommerce service call failed: {}",
          exception.getMessage());

  @Autowired
  public PriceClientImpl(@Qualifier("ecommerceServiceWebClient") WebClient webClient) {
    this.webClient = webClient;
  }

  @Override
  public Mono<Price> getCurrentPriceForProduct(int productId) {
    return webClient.get()
        .uri(uriBuilder -> uriBuilder
            .path(PRICE_BY_PRODUCT_ENDPOINT)
            .pathSegment("{productId}")
            .build(productId))
        .retrieve()
        .bodyToMono(Price.class)
        .doOnError(handleError);
  }

  @Override
  public Flux<Price> getAllPricesForProduct(int productId) {
    return webClient.get()
        .uri(uriBuilder -> uriBuilder
            .path(PRICE_BY_PRODUCT_ENDPOINT)
            .pathSegment("{productId}")
            .pathSegment("all")
            .build(productId))
        .retrieve()
        .bodyToFlux(Price.class)
        .doOnError(handleError);
  }

  @Override
  public Mono<Price> savePrice(Price price) {
    return webClient.post()
        .uri(uriBuilder -> uriBuilder
            .path(PRICE_ENDPOINT)
            .build())
        .bodyValue(price)
        .retrieve()
        .bodyToMono(Price.class)
        .doOnError(handleError);
  }

}
