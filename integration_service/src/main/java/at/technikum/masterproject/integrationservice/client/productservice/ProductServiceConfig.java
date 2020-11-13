package at.technikum.masterproject.integrationservice.client.productservice;

import at.technikum.masterproject.integrationservice.client.WebClientFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
@RequiredArgsConstructor
public class ProductServiceConfig {

  private final ProductServiceProperties productServiceProperties;
  private final WebClientFactory webClientFactory;

  @Bean("productServiceWebClient")
  WebClient productServiceWebClient() {
    return webClientFactory.createFrom(productServiceProperties);
  }

}
