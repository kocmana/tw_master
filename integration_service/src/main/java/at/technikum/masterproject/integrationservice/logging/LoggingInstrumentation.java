package at.technikum.masterproject.integrationservice.logging;

import at.technikum.masterproject.integrationservice.config.LoggingGraphQlContext;
import at.technikum.masterproject.integrationservice.logging.model.LoggingInstrumentationState;
import graphql.ExecutionResult;
import graphql.execution.instrumentation.InstrumentationContext;
import graphql.execution.instrumentation.SimpleInstrumentation;
import graphql.execution.instrumentation.parameters.InstrumentationExecutionParameters;
import java.util.concurrent.CompletableFuture;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;


@Slf4j
public class LoggingInstrumentation extends SimpleInstrumentation {

  private LoggingInstrumentationState loggingState;

  @Override
  public InstrumentationContext<ExecutionResult> beginExecution(InstrumentationExecutionParameters parameters) {
    String executionId = parameters.getExecutionInput().getExecutionId().toString();
    LoggingGraphQlContext state = parameters.getContext();
    loggingState = state.getLoggingState();
    MDC.put(LoggingConstants.CORRELATION_ID, executionId);
    loggingState.addRequest(executionId);
    loggingState.getRequestStatistics(executionId).startRequest();

    return super.beginExecution(parameters);
  }

  @Override
  public CompletableFuture<ExecutionResult> instrumentExecutionResult(
      ExecutionResult executionResult,
      InstrumentationExecutionParameters parameters) {
    String executionId = parameters.getExecutionInput().getExecutionId().toString();
    LoggingGraphQlContext state = parameters.getContext();
    loggingState = state.getLoggingState();
    loggingState.getRequestStatistics(executionId).stopRequest();
    log.info(loggingState.getRequestStatistics(executionId).toString());
    MDC.remove(executionId);
    return super.instrumentExecutionResult(executionResult, parameters);
  }

}
