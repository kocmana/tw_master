package at.technikum.masterproject.integrationservice.productservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class ProductServiceConfiguration {

  private final RestTemplateBuilder restTemplateBuilder;
  private final ProductServiceProperties productServiceProperties;

  @Bean
  WebClient productServiceWebclient(){
    return WebClient.builder()
        .baseUrl("http://localhost:8081")
        .defaultHeaders(header -> header.setBasicAuth("admin", "adminPassword"))
        .build();
  }

  @Autowired
  public ProductServiceConfiguration(RestTemplateBuilder restTemplateBuilder,
      ProductServiceProperties productServiceProperties) {
    this.restTemplateBuilder = restTemplateBuilder;
    this.productServiceProperties = productServiceProperties;
  }

  RestTemplate generateProductServiceRestTemplate() {
    setBasicAuth();
    setRootUri();
    return restTemplateBuilder.build();
  }

  private void setBasicAuth() {
    restTemplateBuilder.basicAuthentication(productServiceProperties.getUsername(),
        productServiceProperties.getPassword());
  }

  private void setRootUri() {
    String rootUri = productServiceProperties.getUrl()
        .concat(":")
        .concat(String.valueOf(productServiceProperties.getPort()));
    restTemplateBuilder.rootUri(rootUri);

  }
}
