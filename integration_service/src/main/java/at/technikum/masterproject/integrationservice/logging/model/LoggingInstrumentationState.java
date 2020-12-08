package at.technikum.masterproject.integrationservice.logging.model;

import graphql.execution.instrumentation.InstrumentationState;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@ToString
public class LoggingInstrumentationState implements InstrumentationState {

  private ConcurrentMap<String, RequestStatistics> requestMap = new ConcurrentHashMap<>();

  public void addRequest(String executionId) {
    if (requestMap.containsKey(executionId)) {
      String errorMessage = String.format("Request for execution ID %s already logged.", executionId);
      throw new IllegalArgumentException(errorMessage);
    }

    requestMap.put(executionId, new RequestStatistics());
  }

  public RequestStatistics getRequestStatistics(String executionId) {
    return requestMap.get(executionId);
  }

  public void addDownstreamCallToRequestLog(String executionId, DownstreamRequest downstreamRequest) {
    assertThatRequestExists(executionId);
    RequestStatistics request = requestMap.get(executionId);
    request.addDownstreamRequest(downstreamRequest);
  }

  private void assertThatRequestExists(String executionId) {
    if (!requestMap.containsKey(executionId)) {
      String errorMessage =
          String.format("No request for execution ID %s found in Logging Instrumentation.", executionId);
      throw new IllegalArgumentException(errorMessage);
    }
  }

}
