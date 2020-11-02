package at.technikum.masterproject.integrationservice.client.ecommerceservice;

import at.technikum.masterproject.integrationservice.client.RestTemplateFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class EcommerceServiceConfig {

  private final EcommerceServiceProperties ecommerceServiceProperties;
  private final RestTemplateFactory restTemplateFactory;

  @Autowired
  EcommerceServiceConfig(EcommerceServiceProperties ecommerceServiceProperties,
      RestTemplateFactory restTemplateFactory) {
    this.ecommerceServiceProperties = ecommerceServiceProperties;
    this.restTemplateFactory = restTemplateFactory;
  }

  @Bean("ecommerceServiceRestTemplate")
  RestTemplate ecommerceServiceWebclient() {
    return restTemplateFactory.createFrom(ecommerceServiceProperties);
  }

}
