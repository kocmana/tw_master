package at.technikum.masterproject.integrationservice.resolver.async;

import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

public interface ResolverExecutor {

  <T> CompletableFuture<T> resolve(Supplier<T> supplier);

  void run(Runnable runnable);
}
