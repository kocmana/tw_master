package at.technikum.masterproject.integrationservice.logging;

import static at.technikum.masterproject.integrationservice.logging.LoggingConstants.CORRELATION_ID;
import static at.technikum.masterproject.integrationservice.logging.LoggingConstants.REQUEST_ID_KEY;

import at.technikum.masterproject.integrationservice.logging.model.DownstreamRequest;
import at.technikum.masterproject.integrationservice.logging.model.LoggingInstrumentationState;
import java.io.IOException;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.jboss.logging.MDC;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

@RequiredArgsConstructor
public class RestTemplateRequestIdInterceptor implements ClientHttpRequestInterceptor {

  private final LoggingInstrumentationState loggingInstrumentationState;

  @Override
  public ClientHttpResponse intercept(HttpRequest request, byte[] body,
                                      ClientHttpRequestExecution execution)
      throws IOException {

    ClientHttpResponse response = execution.execute(request, body);

    DownstreamRequest downstreamLogEntry = createDownstreamRequestEntry(request, response);
    String executionId = (String) MDC.get(CORRELATION_ID);
    loggingInstrumentationState.addDownstreamCallToRequestLog(executionId, downstreamLogEntry);

    return response;
  }

  private DownstreamRequest createDownstreamRequestEntry(HttpRequest request, ClientHttpResponse response) {
    try {
      return DownstreamRequest.builder()
          .id(extractDownstreamRequestIdFromResponseHeaders(response))
          .endpoint(request.getURI())
          .method(request.getMethod())
          .returnCode(response.getStatusCode())
          .build();
    } catch (IOException e) {
      throw new IllegalStateException("IO Exception occurred during logging of request.", e);
    }
  }

  private String extractDownstreamRequestIdFromResponseHeaders(ClientHttpResponse response) {
    HttpHeaders headers = response.getHeaders();
    return Optional.ofNullable(headers.get(REQUEST_ID_KEY))
        .filter(list -> list.size() == 1)
        .map(list -> list.get(0))
        .orElse("No request ID found.");
  }

}
