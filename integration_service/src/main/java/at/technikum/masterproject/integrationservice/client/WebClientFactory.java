package at.technikum.masterproject.integrationservice.client;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Arrays;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class WebClientFactory {

    public WebClient createFrom(ServiceProperties properties) {
        WebClient.Builder builder = WebClient.builder()
                .baseUrl(setRootUri(properties));

        switch (properties.getAuthenticationType()) {
            case API_KEY:
                assertNonNullForProperties(properties.getApiKeyHeader(), properties.getApiKey());
                return builder
                        .defaultHeaders(header -> header.set(
                                properties.getApiKeyHeader(),
                                properties.getApiKey()))
                        .build();
            case BASIC_AUTH:
                assertNonNullForProperties(properties.getUsername(), properties.getPassword());
                return builder
                        .defaultHeaders(header -> header.setBasicAuth(
                                properties.getUsername(),
                                properties.getPassword()))
                        .build();
            default:
                throw new IllegalArgumentException("Unknown authentication type");
        }
    }

  private String setRootUri(ServiceProperties properties) {
    return UriComponentsBuilder.newInstance()
            .scheme("http")
            .host(properties.getUrl())
            .port(properties.getPort())
            .build()
            .toUriString();
  }

  private void assertNonNullForProperties(Object... credentials) {
    if (Arrays.stream(credentials).anyMatch(Objects::isNull)) {
      throw new IllegalArgumentException("Missing credentials for service properties.");
    }
  }
}
