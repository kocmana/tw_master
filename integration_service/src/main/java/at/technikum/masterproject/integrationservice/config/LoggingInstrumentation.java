package at.technikum.masterproject.integrationservice.config;

import at.technikum.masterproject.integrationservice.logging.LoggingInstrumentationState;
import graphql.ExecutionResult;
import graphql.execution.instrumentation.InstrumentationContext;
import graphql.execution.instrumentation.InstrumentationState;
import graphql.execution.instrumentation.SimpleInstrumentation;
import graphql.execution.instrumentation.parameters.InstrumentationExecutionParameters;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LoggingInstrumentation extends SimpleInstrumentation {

  @Override
  public InstrumentationState createState() {
    return new LoggingInstrumentationState();
  }

  @Override
  public InstrumentationContext<ExecutionResult> beginExecution(
      InstrumentationExecutionParameters parameters) {
    LoggingInstrumentationState state = parameters.getInstrumentationState();
    state.getStopWatch().start();
    //MDC.clear();
    //MDC.put("x-request-id", state.getUuid().toString());
    return super.beginExecution(parameters);
  }
//
//  @Override
//  public DataFetcher<?> instrumentDataFetcher(DataFetcher<?> dataFetcher,
//      InstrumentationFieldFetchParameters parameters) {
//    return super.instrumentDataFetcher(dataFetcher, parameters);
//  }
//
//  @Override
//  public CompletableFuture<ExecutionResult> instrumentExecutionResult(
//      ExecutionResult executionResult,
//      InstrumentationExecutionParameters parameters) {
//    LoggingInstrumentationState state = parameters.getInstrumentationState();
//    log.info("Query {} finished after {}ms: Requests: {}",
//        parameters.getQuery(),
//        state.getStopWatch().getId(),
//        MDC.getCopyOfContextMap());
//    MDC.clear();
//    return super.instrumentExecutionResult(executionResult, parameters);
//  }
}
