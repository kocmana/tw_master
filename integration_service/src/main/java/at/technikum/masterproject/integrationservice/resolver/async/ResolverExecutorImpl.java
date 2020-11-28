package at.technikum.masterproject.integrationservice.resolver.async;

import at.technikum.masterproject.integrationservice.logging.RequestLoggingPropagationExecutor;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.function.Supplier;

@Component
public class ResolverExecutorImpl implements ResolverExecutor {

  private static final int AVAILABLE_PROCESSORS = Runtime.getRuntime().availableProcessors();
  private static final Executor EXECUTOR = new RequestLoggingPropagationExecutor(Executors.newFixedThreadPool(AVAILABLE_PROCESSORS));

  public <T> CompletableFuture<T> resolve(Supplier<T> supplier) {
    return CompletableFuture.supplyAsync(supplier, EXECUTOR);
  }

  public void run(Runnable runnable) {
    CompletableFuture.runAsync(runnable, EXECUTOR);
  }
}
