package at.technikum.masterproject.integrationservice.logging;

import java.io.IOException;
import java.util.Collections;
import java.util.Optional;
import org.jboss.logging.MDC;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

public class RestTemplateRequestIdInterceptor implements ClientHttpRequestInterceptor {

  private static final String LOG_KEY_PATTERN = "%s %s (%d)";

  @Override
  public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution)
      throws IOException {
    ClientHttpResponse response = execution.execute(request, body);
    HttpHeaders headers = response.getHeaders();
    String requestId = Optional.ofNullable(headers.get("x-request-id"))
        .filter(list -> list.size() == 1)
        .orElse(Collections.singletonList("No request ID found."))
        .get(0);

    String logKey = createLogKey(request, response);

    MDC.put(logKey, requestId);
    return response;
  }

  private String createLogKey(HttpRequest request, ClientHttpResponse response) throws IOException {
    return String.format(LOG_KEY_PATTERN,
        request.getMethodValue(), request.getURI(),
        response.getRawStatusCode());
  }
}
