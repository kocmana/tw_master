package at.technikum.masterproject.integrationservice.client;

import java.util.Arrays;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Component
public class RestTemplateFactory {

  private final RestTemplateBuilder restTemplateBuilder;

  @Autowired
  public RestTemplateFactory(RestTemplateBuilder restTemplateBuilder) {
    this.restTemplateBuilder = restTemplateBuilder;
  }

  private String setRootUri(ServiceProperties properties) {
    return UriComponentsBuilder.newInstance()
        .scheme("http")
        .host(properties.getUrl())
        .port(properties.getPort())
        .build()
        .toUriString();
  }

  public RestTemplate createFrom(ServiceProperties properties) {
    RestTemplateBuilder builder = restTemplateBuilder
        .rootUri(setRootUri(properties));

    switch (properties.getAuthenticationType()) {
      case API_KEY:
        assertNonNullForProperties(properties.getApiKeyHeader(), properties.getApiKey());
        return builder
            .defaultHeader(
                properties.getApiKeyHeader(),
                properties.getApiKey())
            .build();
      case BASIC_AUTH:
        assertNonNullForProperties(properties.getUsername(), properties.getPassword());
        return builder
            .basicAuthentication(
                properties.getUsername(),
                properties.getPassword())
            .build();
      default:
        throw new IllegalArgumentException("Unknown authentication type");
    }
  }

  private void assertNonNullForProperties(Object... credentials){
    if(Arrays.stream(credentials).anyMatch(Objects::isNull)) {
      throw new IllegalArgumentException("Missing credentials for service properties.");
    }
  }
}
