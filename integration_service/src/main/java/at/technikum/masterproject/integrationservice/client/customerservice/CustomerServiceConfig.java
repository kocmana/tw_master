package at.technikum.masterproject.integrationservice.client.customerservice;

import at.technikum.masterproject.integrationservice.client.RestTemplateFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
@RequiredArgsConstructor
public class CustomerServiceConfig {

  private final CustomerServiceProperties customerServiceProperties;
  private final RestTemplateFactory restTemplateFactory;

  @Bean("customerServiceRestTemplate")
  RestTemplate restTemplate(RestTemplateBuilder restTemplateBuilder) {
    return restTemplateFactory.createFrom(customerServiceProperties);
  }
}
