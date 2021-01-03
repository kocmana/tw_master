package at.technikum.masterproject.integrationservice.timeout;

import graphql.ExecutionResult;
import graphql.execution.instrumentation.InstrumentationContext;
import graphql.execution.instrumentation.InstrumentationState;
import graphql.execution.instrumentation.SimpleInstrumentation;
import graphql.execution.instrumentation.parameters.InstrumentationExecutionParameters;
import graphql.execution.instrumentation.parameters.InstrumentationFieldFetchParameters;
import graphql.execution.instrumentation.parameters.InstrumentationFieldParameters;
import graphql.schema.DataFetcher;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StopWatch;


@Slf4j
public class MaxQueryDurationInstrumentation extends SimpleInstrumentation {

  private long maxDuration;

  public MaxQueryDurationInstrumentation() {
    log.info("Loaded max query duration instrumentation.");
  }

  @Value("${graphql.servlet.maxQueryDuration:10_000}")
  public void setMaxDuration(long maxDuration) {
    this.maxDuration = maxDuration;
  }

  @Override
  public InstrumentationState createState() {
    return new MaxQueryDurationInstrumentationState();
  }

  @Override
  public DataFetcher<?> instrumentDataFetcher(DataFetcher<?> dataFetcher,
                                              InstrumentationFieldFetchParameters parameters) {

    StopWatch stopWatch = getStopWatchFromState(parameters);
    stopWatch.stop();

    if (stopWatch.getTotalTimeMillis() > maxDuration) {
      stopWatch.start();
      return new TimeoutDataFetcher<>();
    }

    stopWatch.start();
    return super.instrumentDataFetcher(dataFetcher, parameters);
  }

  @SneakyThrows
  @Override
  public InstrumentationContext<ExecutionResult> beginExecution(
      InstrumentationExecutionParameters parameters) {
    getStopWatchFromState(parameters).start("query");
    return super.beginExecution(parameters);
  }

  private StopWatch getStopWatchFromState(InstrumentationFieldParameters parameters) {
    MaxQueryDurationInstrumentationState state = parameters.getInstrumentationState();
    return state.getStopWatch();
  }


  private StopWatch getStopWatchFromState(InstrumentationExecutionParameters parameters) {
    MaxQueryDurationInstrumentationState state = parameters.getInstrumentationState();
    return state.getStopWatch();
  }

}
