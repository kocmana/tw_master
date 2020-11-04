package at.technikum.masterproject.integrationservice.client.ecommerceservice;

import at.technikum.masterproject.integrationservice.client.WebClientFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
@RequiredArgsConstructor
public class EcommerceServiceConfig {

  private final EcommerceServiceProperties ecommerceServiceProperties;
  private final WebClientFactory webClientFactory;

  @Bean("ecommerceServiceWebClient")
  WebClient ecommerceServiceWebClient() {
    return webClientFactory.createFrom(ecommerceServiceProperties);
  }

}
