package at.technikum.masterproject.integrationservice.client.productservice;

import at.technikum.masterproject.integrationservice.client.RestTemplateFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ProductServiceConfiguration {

  private final ProductServiceProperties productServiceProperties;
  private final RestTemplateFactory restTemplateFactory;

  @Autowired
  ProductServiceConfiguration(ProductServiceProperties productServiceProperties,
      RestTemplateFactory restTemplateFactory) {
    this.productServiceProperties = productServiceProperties;
    this.restTemplateFactory = restTemplateFactory;
  }

  @Bean("productServiceRestTemplate")
  RestTemplate productServiceWebclient(RestTemplateBuilder restTemplateBuilder) {
    return restTemplateFactory.createFrom(productServiceProperties);
  }

}
