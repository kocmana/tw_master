package at.technikum.masterproject.integrationservice.timeout;

import graphql.execution.instrumentation.InstrumentationState;
import lombok.NoArgsConstructor;
import org.springframework.util.StopWatch;

@NoArgsConstructor
public class MaxQueryInstrumentationState implements InstrumentationState {

  private final StopWatch stopWatch = new StopWatch();

  public Long getTime() {
    return stopWatch.getTotalTimeMillis();
  }

  public void startStopWatch() {
    synchronized (stopWatch) {
      if (!stopWatch.isRunning()) {
        stopWatch.start();
      }
    }
  }

  public void stopStopWatch() {
    synchronized (stopWatch) {
      if (stopWatch.isRunning()) {
        stopWatch.stop();
      }
    }
  }

}
