package at.technikum.masterproject.integrationservice.customerservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;

@Configuration
public class CustomerServiceConfig {

  private final CustomerServiceProperties customerServiceProperties;

  @Autowired
  CustomerServiceConfig(CustomerServiceProperties customerServiceProperties) {
    this.customerServiceProperties = customerServiceProperties;
  }

  @Bean("customerServiceWebClient")
  WebClient productServiceWebclient() {
    return WebClient.builder()
        .baseUrl(setRootUri())
        .defaultHeaders(headers -> headers.add(
            customerServiceProperties.getApiKeyHeader(),
            customerServiceProperties.getApiKey()))
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
