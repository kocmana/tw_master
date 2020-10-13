package at.technikum.masterproject.integrationservice.logging;

import graphql.ExecutionResult;
import graphql.execution.instrumentation.InstrumentationContext;
import graphql.execution.instrumentation.InstrumentationState;
import graphql.execution.instrumentation.SimpleInstrumentation;
import graphql.execution.instrumentation.parameters.InstrumentationExecutionParameters;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;


@Slf4j
public class LoggingInstrumentation extends SimpleInstrumentation {

  private static final String REQUEST_ID_KEY = "x-request-id";
  private static final String BASIC_LOG_ENTRY_PATTERN = "{}: Query:\n{}\nStatus: finished after {}ms,\nRequests:\n{}";
  private static final String DOWNSTREAM_LOG_ENTRY_PATTERN = "\t%s - ID: %s\n";

  @Override
  public InstrumentationState createState() {
    return new LoggingInstrumentationState();
  }

  @Override
  public InstrumentationContext<ExecutionResult> beginExecution(
      InstrumentationExecutionParameters parameters) {
    LoggingInstrumentationState state = parameters.getInstrumentationState();
    state.getStopWatch().start();
    MDC.clear();
    MDC.put(REQUEST_ID_KEY, state.getUuid().toString());
    return super.beginExecution(parameters);
  }

  @Override
  public CompletableFuture<ExecutionResult> instrumentExecutionResult(
      ExecutionResult executionResult,
      InstrumentationExecutionParameters parameters) {
    LoggingInstrumentationState state = parameters.getInstrumentationState();
    state.getStopWatch().stop();
    String requestId = extractAndDeleteRequestId();
    log.info(BASIC_LOG_ENTRY_PATTERN,
        requestId,
        parameters.getQuery(),
        state.getStopWatch().getTotalTimeMillis(),
        createRequestLogFromContextMap(MDC.getCopyOfContextMap()));
    MDC.clear();
    return super.instrumentExecutionResult(executionResult, parameters);
  }

  private String extractAndDeleteRequestId() {
    String requestId = MDC.get(REQUEST_ID_KEY);
    MDC.remove(REQUEST_ID_KEY);
    return requestId;
  }

  private String createRequestLogFromContextMap(Map<String, String> contextMap) {
    StringBuilder sb = new StringBuilder();
    contextMap.forEach((key, value) -> sb.append(String.format(DOWNSTREAM_LOG_ENTRY_PATTERN, key, value)));
    return sb.toString();
  }
}
