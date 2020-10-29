package at.technikum.masterproject.benchmarkservice.service;

import at.technikum.masterproject.benchmarkservice.model.Benchmark;
import at.technikum.masterproject.benchmarkservice.repository.BenchmarkRepository;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class BenchmarkRunner {

  private final GraphQlService graphQlService;
  private final BenchmarkRepository benchmarkRepository;
  private final ExecutorService benchmarkExecutor;

  @Autowired
  public BenchmarkRunner(GraphQlService graphQlService,
      BenchmarkRepository benchmarkRepository) {
    this.graphQlService = graphQlService;
    this.benchmarkRepository = benchmarkRepository;
    benchmarkExecutor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() - 2);
  }

  public void run(Benchmark benchmarkRequest) {
    log.info("Starting benchmark {} for schema {}.",
        benchmarkRequest.getUuid(), benchmarkRequest.getSchema());
    benchmarkRepository.saveAndFlush(benchmarkRequest);

    CompletableFuture.runAsync(() -> {
      doBenchmark(benchmarkRequest);
      setBenchmarkStatusAsFinished(benchmarkRequest);
      log.info("Benchmark {} ({}) finished.", benchmarkRequest.getUuid(), benchmarkRequest.getSchema());
    }, benchmarkExecutor);
  }

  public void doBenchmark(Benchmark benchmark) {
    IntStream.rangeClosed(1, benchmark.getNumberOfCalls())
        .mapToObj(i -> {
          log.info("Benchmark {}: Doing request {}/{} using {}",
              benchmark.getUuid(), i, benchmark.getNumberOfCalls(), benchmark.getSchema());
          return graphQlService.doBenchmarkCall(benchmark);
        })
        .forEach(graphQlService::saveResult);
  }

  public void setBenchmarkStatusAsFinished(Benchmark benchmark) {
    benchmark.setFinished(true);
    benchmarkRepository.saveAndFlush(benchmark);
  }
}
