package at.technikum.masterproject.integrationservice.client.ecommerceservice;

import at.technikum.masterproject.integrationservice.client.RestTemplateFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
@RequiredArgsConstructor
public class EcommerceServiceConfig {

  private final EcommerceServiceProperties ecommerceServiceProperties;
  private final RestTemplateFactory restTemplateFactory;

  @Bean
  @EcommerceService
  RestTemplate ecommerceServiceWebclient() {
    return restTemplateFactory.createFrom(ecommerceServiceProperties);
  }

}
