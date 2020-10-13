package at.technikum.masterproject.integrationservice.logging;

import graphql.execution.instrumentation.InstrumentationState;
import java.util.Map;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;
import org.springframework.util.StopWatch;

@Getter
@Setter
public class LoggingInstrumentationState implements InstrumentationState {

  private UUID uuid = UUID.randomUUID();
  private StopWatch stopWatch;
  private Map<String, String> downstreamRequestIds;
}
