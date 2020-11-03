package at.technikum.masterproject.integrationservice.resolver.async;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Supplier;
import org.springframework.stereotype.Component;

@Component
public class ResolverExecutorImpl implements ResolverExecutor {

  private static final ExecutorService EXECUTOR_SERVICE = Executors.newCachedThreadPool();

  public <T> CompletableFuture<T> resolve(Supplier<T> supplier) {
    return CompletableFuture.supplyAsync(supplier, EXECUTOR_SERVICE);
  }

  public void run(Runnable runnable) {
    CompletableFuture.runAsync(runnable, EXECUTOR_SERVICE);
  }
}
