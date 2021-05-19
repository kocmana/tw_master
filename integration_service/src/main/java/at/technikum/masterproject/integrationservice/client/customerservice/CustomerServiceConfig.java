package at.technikum.masterproject.integrationservice.client.customerservice;

import at.technikum.masterproject.integrationservice.client.RestTemplateFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
@RequiredArgsConstructor
public class CustomerServiceConfig {

  private final CustomerServiceProperties customerServiceProperties;
  private final RestTemplateFactory restTemplateFactory;

  @Bean
  @CustomerService
  RestTemplate restTemplate() {
    return restTemplateFactory.createFrom(customerServiceProperties);
  }
}
