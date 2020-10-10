package at.technikum.masterproject.integrationservice.client.productservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Configuration
public class ProductServiceConfiguration {

  private final ProductServiceProperties productServiceProperties;

  @Autowired
  ProductServiceConfiguration(ProductServiceProperties productServiceProperties) {
    this.productServiceProperties = productServiceProperties;
  }

  @Bean("productServiceRestTemplate")
  RestTemplate productServiceWebclient(RestTemplateBuilder restTemplateBuilder) {
    return restTemplateBuilder
        .rootUri(setRootUri())
        .basicAuthentication(productServiceProperties.getUsername(),
            productServiceProperties.getPassword())
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
