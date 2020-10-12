package at.technikum.masterproject.integrationservice.client.customerservice;

import at.technikum.masterproject.integrationservice.logging.RestTemplateRequestIdInterceptor;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Configuration
public class CustomerServiceConfig {

  private final CustomerServiceProperties customerServiceProperties;

  @Autowired
  CustomerServiceConfig(CustomerServiceProperties customerServiceProperties) {
    this.customerServiceProperties = customerServiceProperties;
  }

  @Bean("customerServiceRestTemplate")
  RestTemplate restTemplate(RestTemplateBuilder restTemplateBuilder) {
    RestTemplate restTemplate = restTemplateBuilder
        .defaultHeader(customerServiceProperties.getApiKeyHeader(),
            customerServiceProperties.getApiKey())
        .rootUri(setRootUri())
        .build();

    List<ClientHttpRequestInterceptor> interceptors = restTemplate
        .getInterceptors();
    if (interceptors.isEmpty()) {
      interceptors = new ArrayList<ClientHttpRequestInterceptor>();
    }
    interceptors.add(new RestTemplateRequestIdInterceptor());
    restTemplate.setInterceptors(interceptors);

    return restTemplate;
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
