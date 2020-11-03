package at.technikum.masterproject.integrationservice.client.customerservice;

import at.technikum.masterproject.integrationservice.client.WebClientFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
@RequiredArgsConstructor
public class CustomerServiceConfig {

  private final CustomerServiceProperties customerServiceProperties;
  private final WebClientFactory webClientFactory;

  @Bean("customerServiceWebClient")
  WebClient webClient() {
    return webClientFactory.createFrom(customerServiceProperties);
  }
}
