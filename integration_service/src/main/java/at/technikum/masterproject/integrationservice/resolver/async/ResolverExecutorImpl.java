package at.technikum.masterproject.integrationservice.resolver.async;

import at.technikum.masterproject.integrationservice.logging.RequestLoggingPropagationExecutor;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.function.Supplier;
import org.springframework.stereotype.Component;

@Component
public class ResolverExecutorImpl implements ResolverExecutor {

  private static final Executor EXECUTOR = new RequestLoggingPropagationExecutor(Executors.newCachedThreadPool());

  public <T> CompletableFuture<T> resolve(Supplier<T> supplier) {
    return CompletableFuture.supplyAsync(supplier, EXECUTOR);
  }

  public void run(Runnable runnable) {
    CompletableFuture.runAsync(runnable, EXECUTOR);
  }
}
