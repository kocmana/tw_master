package at.technikum.masterproject.integrationservice.timeout;

import graphql.ExecutionResult;
import graphql.execution.instrumentation.InstrumentationContext;
import graphql.execution.instrumentation.InstrumentationState;
import graphql.execution.instrumentation.SimpleInstrumentation;
import graphql.execution.instrumentation.parameters.InstrumentationExecutionParameters;
import graphql.execution.instrumentation.parameters.InstrumentationFieldFetchParameters;
import graphql.schema.DataFetcher;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;


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
    return new MaxQueryInstrumentationState();
  }

  @Override
  public DataFetcher<?> instrumentDataFetcher(DataFetcher<?> dataFetcher,
                                              InstrumentationFieldFetchParameters parameters) {

    MaxQueryInstrumentationState state = parameters.getInstrumentationState();

    log.info("Max Duration: {}, current Time: {}", maxDuration, state.getTime());
    if (state.getTime() > maxDuration) {
      return new TimeoutDataFetcher<>();
    }
    return super.instrumentDataFetcher(dataFetcher, parameters);
  }

  @SneakyThrows
  @Override
  public InstrumentationContext<ExecutionResult> beginExecution(
      InstrumentationExecutionParameters parameters) {
    return super.beginExecution(parameters);
  }

}
