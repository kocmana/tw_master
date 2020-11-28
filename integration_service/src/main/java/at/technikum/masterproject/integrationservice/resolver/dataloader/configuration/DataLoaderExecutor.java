package at.technikum.masterproject.integrationservice.resolver.dataloader.configuration;

import at.technikum.masterproject.integrationservice.logging.RequestLoggingPropagationExecutor;
import lombok.extern.slf4j.Slf4j;
import org.dataloader.MappedBatchLoader;
import org.springframework.stereotype.Service;

import java.util.AbstractMap;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toMap;

@Service
@Slf4j
public class DataLoaderExecutor {
  private static final int AVAILABLE_PROCESSORS = Runtime.getRuntime().availableProcessors();
  private static final Executor EXECUTOR = new RequestLoggingPropagationExecutor(Executors.newFixedThreadPool(AVAILABLE_PROCESSORS));

  public <K, V> MappedBatchLoader<K, V> generateBatchLoadFunction(Function<K, V> resultSupplier, Function<V, K> idGenerator) {
    return (Set<K> idSet) -> {
      log.info("Fetching information for the following ids: {}", idSet.toString());

      List<CompletableFuture<V>> futures = idSet.stream()
              .map(id -> CompletableFuture
                      .supplyAsync(() -> resultSupplier.apply(id), EXECUTOR))
              .collect(Collectors.toUnmodifiableList());

      return CompletableFuture.allOf(futures.toArray(new CompletableFuture<?>[0]))
              .thenApply(v -> futures.stream()
                      .map(CompletableFuture::join)
                      .collect(toMap(idGenerator, Function.identity())));
    };
  }

  public <K, V> MappedBatchLoader<K, V> generateBatchLoadFunction(Function<K, V> resultSupplier) {
    return (Set<K> idSet) -> {
      log.info("Fetching information for the following ids: {}", idSet.toString());

      List<CompletableFuture<AbstractMap.SimpleImmutableEntry<K, V>>> futures = idSet.stream()
              .map(id -> CompletableFuture
                      .supplyAsync(() -> new AbstractMap.SimpleImmutableEntry<K, V>(id, resultSupplier.apply(id)),
                              EXECUTOR))
              .collect(Collectors.toUnmodifiableList());

      return CompletableFuture.allOf(futures.toArray(new CompletableFuture<?>[0]))
              .thenApply(v -> futures.stream()
                      .map(CompletableFuture::join)
                      .collect(toMap(AbstractMap.SimpleImmutableEntry::getKey, AbstractMap.SimpleImmutableEntry::getValue)));
    };
  }

}
