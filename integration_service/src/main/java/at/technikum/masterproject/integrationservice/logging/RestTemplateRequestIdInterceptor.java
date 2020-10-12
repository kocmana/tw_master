package at.technikum.masterproject.integrationservice.logging;

import java.io.IOException;
import java.util.Collections;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

@Slf4j
public class RestTemplateRequestIdInterceptor implements ClientHttpRequestInterceptor {

  @Override
  public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution)
      throws IOException {
    ClientHttpResponse response = execution.execute(request, body);
    HttpHeaders headers = response.getHeaders();
    String requestId = Optional.ofNullable(headers.get("x-request-id"))
        .filter(list -> list.size() == 1)
        .orElse(Collections.singletonList("No request ID found."))
        .get(0);

    log.info("Request ID: {}", requestId);
    return response;
  }
}
