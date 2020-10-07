package at.technikum.masterproject.integrationservice.client.ecommerceservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;

@Configuration
public class EcommerceServiceConfig {

  private final EcommerceServiceProperties ecommerceServiceProperties;

  @Autowired
  EcommerceServiceConfig(EcommerceServiceProperties ecommerceServiceProperties) {
    this.ecommerceServiceProperties = ecommerceServiceProperties;
  }

  @Bean("ecommerceServiceWebClient")
  WebClient ecommerceServiceWebclient() {
    return WebClient.builder()
        .baseUrl(setRootUri())
        .defaultHeaders(header -> header.setBasicAuth(
            ecommerceServiceProperties.getUsername(),
            ecommerceServiceProperties.getPassword()))
        .build();
  }

  private String setRootUri() {
    return UriComponentsBuilder.newInstance()
        .scheme("http")
        .host(ecommerceServiceProperties.getUrl())
        .port(ecommerceServiceProperties.getPort())
        .build()
        .toUriString();
  }
}
