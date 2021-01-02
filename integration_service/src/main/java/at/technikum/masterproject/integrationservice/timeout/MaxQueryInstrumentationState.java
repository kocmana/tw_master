package at.technikum.masterproject.integrationservice.timeout;

import graphql.execution.instrumentation.InstrumentationState;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.StopWatch;

@Getter
@NoArgsConstructor
public class MaxQueryInstrumentationState implements InstrumentationState {

  private final StopWatch stopWatch = new StopWatch();
}
