package at.technikum.masterproject.integrationservice.client.productservice;

import at.technikum.masterproject.integrationservice.client.RestTemplateFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
@RequiredArgsConstructor
public class ProductServiceConfig {

  private final ProductServiceProperties productServiceProperties;
  private final RestTemplateFactory restTemplateFactory;

  @Bean
  @ProductService
  RestTemplate productServiceWebclient(RestTemplateBuilder restTemplateBuilder) {
    return restTemplateFactory.createFrom(productServiceProperties);
  }

}
