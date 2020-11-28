package at.technikum.masterproject.integrationservice.logging;

import graphql.execution.instrumentation.InstrumentationState;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentMap;

@Getter
@Setter
@Component
public class LoggingInstrumentationState implements InstrumentationState {

  private ConcurrentMap<String, RequestStatistics> requestMap;

}
