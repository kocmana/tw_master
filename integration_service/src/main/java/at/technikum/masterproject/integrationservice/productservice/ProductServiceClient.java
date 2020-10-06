package at.technikum.masterproject.integrationservice.productservice;

import at.technikum.masterproject.integrationservice.model.Product;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class ProductServiceClient {

  WebClient webClient;

  @Autowired
  public ProductServiceClient(WebClient webClient) {
    this.webClient = webClient;
  }

  public Mono<Product> getProduct(int id){
    return webClient.get()
        .uri("/product/1")
        .retrieve()
        .bodyToMono(Product.class)
        .doOnError(ex -> log.error(ex.getMessage()));
  }
}
