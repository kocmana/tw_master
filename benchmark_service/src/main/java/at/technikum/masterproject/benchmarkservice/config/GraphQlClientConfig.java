package at.technikum.masterproject.benchmarkservice.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import graphql.kickstart.spring.webclient.boot.GraphQLWebClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;

@Configuration
@ConfigurationPropertiesScan(basePackageClasses = ServiceProperties.class)
public class GraphQlClientConfig {

  private final ObjectMapper objectMapper;
  private final ServiceProperties serviceProperties;

  @Autowired
  public GraphQlClientConfig(ObjectMapper objectMapper,
      ServiceProperties serviceProperties) {
    this.objectMapper = objectMapper;
    this.serviceProperties = serviceProperties;
  }

  @Bean
  public GraphQLWebClient graphQlWebClient() {
    WebClient webClient = WebClient.builder()
        .baseUrl(setRootUri())
        .defaultHeaders(httpHeaders -> httpHeaders
            .setBasicAuth(
                serviceProperties.getUsername(),
                serviceProperties.getPassword()))
        .build();
    return GraphQLWebClient.newInstance(webClient, objectMapper);
  }

  private String setRootUri() {
    return UriComponentsBuilder.newInstance()
        .scheme("http")
        .host(serviceProperties.getUrl())
        .port(serviceProperties.getPort())
        .path(serviceProperties.getEndpoint())
        .build()
        .toUriString();
  }
}
