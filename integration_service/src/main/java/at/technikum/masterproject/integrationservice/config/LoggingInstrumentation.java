package at.technikum.masterproject.integrationservice.config;

import graphql.ExecutionResult;
import graphql.execution.instrumentation.InstrumentationContext;
import graphql.execution.instrumentation.SimpleInstrumentation;
import graphql.execution.instrumentation.parameters.InstrumentationExecutionParameters;
import graphql.execution.instrumentation.parameters.InstrumentationFieldCompleteParameters;
import java.util.concurrent.CompletableFuture;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LoggingInstrumentation extends SimpleInstrumentation {

  @Override
  public InstrumentationContext<ExecutionResult> beginExecution(
      InstrumentationExecutionParameters parameters) {
    log.warn("I got a begin execution call with parameters {}", parameters);
    return super.beginExecution(parameters);
  }

  @Override
  public CompletableFuture<ExecutionResult> instrumentExecutionResult(
      ExecutionResult executionResult, InstrumentationExecutionParameters parameters) {
    log.warn("I got a execution result call with parameters {}.", parameters);
    return super.instrumentExecutionResult(executionResult, parameters);
  }

  @Override
  public InstrumentationContext<ExecutionResult> beginFieldListComplete(
      InstrumentationFieldCompleteParameters parameters) {
    log.warn("I got an beginFieldListComplete call with parameters {}", parameters);
    return super.beginFieldListComplete(parameters);
  }
}
