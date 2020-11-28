package at.technikum.masterproject.integrationservice.logging;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.slf4j.MDC;

import java.util.concurrent.Executor;

@RequiredArgsConstructor
@Slf4j
public class RequestLoggingPropagationExecutor implements Executor {

  private final Executor delegate;

  @Override
  public void execute(@NotNull Runnable command) {
    String correlationId = MDC.get("correlation_id");
    try {
      delegate.execute(() -> {
        MDC.put("correlation_id", correlationId);
        command.run();
      });
    } finally {
      //MDC.remove("correlation_id");
    }
  }
}
