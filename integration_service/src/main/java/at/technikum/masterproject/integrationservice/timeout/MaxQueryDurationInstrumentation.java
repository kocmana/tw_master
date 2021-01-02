package at.technikum.masterproject.integrationservice.timeout;

import graphql.ExecutionResult;
import graphql.execution.instrumentation.InstrumentationContext;
import graphql.execution.instrumentation.InstrumentationState;
import graphql.execution.instrumentation.SimpleInstrumentation;
import graphql.execution.instrumentation.SimpleInstrumentationContext;
import graphql.execution.instrumentation.parameters.InstrumentationExecutionParameters;
import graphql.execution.instrumentation.parameters.InstrumentationFieldFetchParameters;
import java.util.concurrent.CompletableFuture;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StopWatch;


@Slf4j
public class MaxQueryDurationInstrumentation extends SimpleInstrumentation {

  private final long maxDuration = 5000L;

  public MaxQueryDurationInstrumentation() {
    log.info("Loaded max duration instrumentation.");
  }

  @Override
  public InstrumentationState createState() {
    return new MaxQueryInstrumentationState();
  }

  @Override
  public InstrumentationContext<Object> beginFieldFetch(
      InstrumentationFieldFetchParameters parameters) {
    MaxQueryInstrumentationState state = parameters.getInstrumentationState();
    StopWatch stopWatch = state.getStopWatch();

    stopWatch.stop();

    log.info("Begin Field Fetch: Current duration: {}, max Duration: {}, Task name",
        stopWatch.getTotalTimeMillis(), maxDuration, stopWatch.getLastTaskName());
    if (stopWatch.getTotalTimeMillis() > maxDuration) {
      stopWatch.start();
      log.warn("Duration too long");
      //throw new QueryTimeoutException("Query took too long");
      return new SimpleInstrumentationContext<>();
    }

    stopWatch.start();

    return super.beginFieldFetch(parameters);
  }

  @SneakyThrows
  @Override
  public InstrumentationContext<ExecutionResult> beginExecution(
      InstrumentationExecutionParameters parameters) {
    getStopWatchFromState(parameters).start("query");
    log.warn("Starting stop watch", getStopWatchFromState(parameters));
    Thread.sleep(1000);
    getStopWatchFromState(parameters).stop();
    long duration = getStopWatchFromState(parameters).getTotalTimeMillis();
    log.warn("Duration after sleep: {}", duration);
    getStopWatchFromState(parameters).start();
    return super.beginExecution(parameters);
  }

  private StopWatch getStopWatchFromState(InstrumentationExecutionParameters parameters) {
    MaxQueryInstrumentationState state = parameters.getInstrumentationState();
    return state.getStopWatch();
  }

  @Override
  public CompletableFuture<ExecutionResult> instrumentExecutionResult(
      ExecutionResult executionResult,
      InstrumentationExecutionParameters parameters) {
    //getStopWatchFromState(parameters).stop();
    return super.instrumentExecutionResult(executionResult, parameters);
  }

}
