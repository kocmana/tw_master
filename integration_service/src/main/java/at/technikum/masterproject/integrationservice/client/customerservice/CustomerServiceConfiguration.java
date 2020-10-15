package at.technikum.masterproject.integrationservice.client.customerservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Configuration
public class CustomerServiceConfiguration {

  private final CustomerServiceProperties customerServiceProperties;

  @Autowired
  CustomerServiceConfiguration(CustomerServiceProperties customerServiceProperties) {
    this.customerServiceProperties = customerServiceProperties;
  }

  @Bean("customerServiceRestTemplate")
  RestTemplate restTemplate(RestTemplateBuilder restTemplateBuilder) {
    return restTemplateBuilder
        .defaultHeader(customerServiceProperties.getApiKeyHeader(),
            customerServiceProperties.getApiKey())
        .rootUri(setRootUri())
        .build();
  }

  private String setRootUri() {
    return UriComponentsBuilder.newInstance()
        .scheme("http")
        .host(customerServiceProperties.getUrl())
        .port(customerServiceProperties.getPort())
        .build()
        .toUriString();
  }
}
