package at.technikum.masterproject.integrationservice.client.productservice;

import static org.springframework.http.HttpMethod.PATCH;

import at.technikum.masterproject.integrationservice.model.product.Product;
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
public class ProductInformationClientImpl implements ProductInformationClient {

  private static final String PRODUCT_ENDPOINT = "product";

  private final WebClient webClient;
  private final Consumer<? super Throwable> handleError = exception -> log.info("Product service call failed: {}",
      exception.getMessage());

  @Autowired
  public ProductInformationClientImpl(@Qualifier("productServiceWebClient") WebClient webClient) {
    this.webClient = webClient;
  }

  @Override
  public Mono<Product> getProductById(int id) {
    return webClient.get()
        .uri(uriBuilder -> uriBuilder
            .path(PRODUCT_ENDPOINT)
            .pathSegment("{id}")
            .build(id))
        .retrieve()
        .bodyToMono(Product.class)
        .doOnError(handleError);
  }

  @Override
  public Flux<Product> getAllProducts() {
    return webClient.get()
        .uri(uriBuilder -> uriBuilder
            .path(PRODUCT_ENDPOINT)
            .build())
        .retrieve()
        .bodyToFlux(Product.class)
        .doOnError(handleError);
  }

  @Override
  public Mono<Product> saveProduct(Product product) {
    return webClient.post()
        .uri(uriBuilder -> uriBuilder
            .path(PRODUCT_ENDPOINT)
            .build())
        .bodyValue(product)
        .retrieve()
        .bodyToMono(Product.class)
        .doOnError(handleError);
  }

  @Override
  public Mono<Product> updateProduct(Product product) {
    return webClient.method(PATCH)
        .uri(uriBuilder -> uriBuilder
            .path(PRODUCT_ENDPOINT)
            .build())
        .bodyValue(product)
        .retrieve()
        .bodyToMono(Product.class)
        .doOnError(handleError);
  }

}
