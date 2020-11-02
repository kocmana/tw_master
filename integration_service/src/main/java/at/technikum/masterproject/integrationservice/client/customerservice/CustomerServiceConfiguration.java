package at.technikum.masterproject.integrationservice.client.customerservice;

import at.technikum.masterproject.integrationservice.client.RestTemplateFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class CustomerServiceConfiguration {

  private final CustomerServiceProperties customerServiceProperties;
  private final RestTemplateFactory restTemplateFactory;

  @Autowired
  CustomerServiceConfiguration(CustomerServiceProperties customerServiceProperties,
      RestTemplateFactory restTemplateFactory) {
    this.customerServiceProperties = customerServiceProperties;
    this.restTemplateFactory = restTemplateFactory;
  }

  @Bean("customerServiceRestTemplate")
  RestTemplate restTemplate(RestTemplateBuilder restTemplateBuilder) {
    return restTemplateFactory.createFrom(customerServiceProperties);
  }
}
