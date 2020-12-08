package at.technikum.masterproject.integrationservice.logging;

import static at.technikum.masterproject.integrationservice.logging.LoggingConstants.CORRELATION_ID;

import java.util.concurrent.Executor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.slf4j.MDC;

@RequiredArgsConstructor
@Slf4j
public class RequestLoggingPropagationExecutor implements Executor {

  private final Executor delegate;

  @Override
  public void execute(@NotNull Runnable command) {
    String correlationId = MDC.get(CORRELATION_ID);
    delegate.execute(() -> {
      try {

        MDC.put(CORRELATION_ID, correlationId);
        command.run();
      } finally {
        MDC.remove(CORRELATION_ID);
      }
    });
  }

}
