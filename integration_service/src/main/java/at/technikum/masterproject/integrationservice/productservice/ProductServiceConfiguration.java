package at.technikum.masterproject.integrationservice.productservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;

@Configuration
public class ProductServiceConfiguration {

  private final ProductServiceProperties productServiceProperties;

  @Autowired
  ProductServiceConfiguration(ProductServiceProperties productServiceProperties) {
    this.productServiceProperties = productServiceProperties;
  }

  @Bean("productServiceWebClient")
  WebClient productServiceWebclient() {
    return WebClient.builder()
        .baseUrl(setRootUri())
        .defaultHeaders(header -> header.setBasicAuth(
            productServiceProperties.getUsername(),
            productServiceProperties.getPassword()))
        .build();
  }

  private String setRootUri() {
    return UriComponentsBuilder.newInstance()
        .scheme("http")
        .host(productServiceProperties.getUrl())
        .port(productServiceProperties.getPort())
        .build()
        .toUriString();
  }
}
