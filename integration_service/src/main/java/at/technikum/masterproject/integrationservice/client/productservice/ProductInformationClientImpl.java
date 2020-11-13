package at.technikum.masterproject.integrationservice.client.productservice;

import at.technikum.masterproject.integrationservice.model.product.Product;
import at.technikum.masterproject.integrationservice.model.product.dto.CreateProductInput;
import at.technikum.masterproject.integrationservice.model.product.dto.UpdateProductInput;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class ProductInformationClientImpl implements ProductInformationClient {

  private static final String PRODUCT_ENDPOINT = "product";

  private final WebClient webClient;

  @Autowired
  public ProductInformationClientImpl(
      @Qualifier("productServiceWebClient") WebClient webClient) {
    this.webClient = webClient;
  }

  @Override
  public Mono<Product> getProductById(int productId) {
    return webClient.get()
        .uri(uriBuilder -> uriBuilder
            .pathSegment(PRODUCT_ENDPOINT)
            .pathSegment("{productId}")
            .build(productId))
        .retrieve()
        .bodyToMono(Product.class)
        .retry(2);
  }

  @Override
  public Mono<List<Product>> getAllProducts() {
    return webClient.get()
        .uri(uriBuilder -> uriBuilder
            .pathSegment(PRODUCT_ENDPOINT)
            .build())
        .retrieve()
        .bodyToFlux(Product.class)
        .retry(2)
        .collectList();
  }

  @Override
  public Mono<Integer> saveProduct(CreateProductInput product) {
    return webClient.post()
        .uri(uriBuilder -> uriBuilder
            .pathSegment(PRODUCT_ENDPOINT)
            .build())
        .bodyValue(product)
        .retrieve()
        .bodyToMono(Product.class)
        .retry(2)
        .map(Product::getId);
  }

  @Override
  public void updateProduct(UpdateProductInput product) {
    webClient.put()
        .uri(uriBuilder -> uriBuilder
            .pathSegment(PRODUCT_ENDPOINT)
            .build())
        .bodyValue(product)
        .retrieve();
  }

  @Override
  public void deleteProduct(int productId) {
    webClient.delete()
        .uri(uriBuilder -> uriBuilder
            .pathSegment(PRODUCT_ENDPOINT)
            .pathSegment("{productId}")
            .build(productId))
        .retrieve();
  }
}
