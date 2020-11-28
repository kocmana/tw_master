package at.technikum.masterproject.integrationservice.logging;

import graphql.ExecutionResult;
import graphql.execution.ExecutionId;
import graphql.execution.instrumentation.InstrumentationContext;
import graphql.execution.instrumentation.InstrumentationState;
import graphql.execution.instrumentation.SimpleInstrumentation;
import graphql.execution.instrumentation.parameters.InstrumentationExecutionParameters;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

import java.util.concurrent.CompletableFuture;


@Slf4j
public class LoggingInstrumentation extends SimpleInstrumentation {

  private LoggingInstrumentationState state;

  private static final String REQUEST_ID_KEY = "x-request-id";

  private static final String BASIC_LOG_ENTRY_PATTERN = "{}: Query:\n{}\nStatus: finished after {}ms,\nRequests:\n{}";
  private static final String DOWNSTREAM_LOG_ENTRY_PATTERN = "\t%s - ID: %s\n";

  @Autowired
  public void setState(LoggingInstrumentationState state) {
    this.state = state;
  }

  @Override
  @Bean
  public InstrumentationState createState() {
    return state;
  }

  @Override
  public InstrumentationContext<ExecutionResult> beginExecution(InstrumentationExecutionParameters parameters) {
    ExecutionId executionId = parameters.getExecutionInput().getExecutionId();
    MDC.put("correlation_id", executionId.toString());
    return super.beginExecution(parameters);
  }

  @Override
  public CompletableFuture<ExecutionResult> instrumentExecutionResult(
          ExecutionResult executionResult,
          InstrumentationExecutionParameters parameters) {
    LoggingInstrumentationState state = parameters.getInstrumentationState();
    MDC.clear();
    return super.instrumentExecutionResult(executionResult, parameters);
  }

}
