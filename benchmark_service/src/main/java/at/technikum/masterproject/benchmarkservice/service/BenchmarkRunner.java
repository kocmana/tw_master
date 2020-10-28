package at.technikum.masterproject.benchmarkservice.service;

import java.util.concurrent.CompletableFuture;
import java.util.stream.IntStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

@Service
public class BenchmarkRunner implements CommandLineRunner {

  private final GraphQlService graphQlService;

  @Autowired
  public BenchmarkRunner(GraphQlService graphQlService) {
    this.graphQlService = graphQlService;
  }

  @Override
  public void run(String... args) throws Exception {
    CompletableFuture.runAsync(() ->
        doBenchmark("schemas/benchmark.graphqls", 100));
  }

  public void doBenchmark(String schema, int numberOfCalls) {
    IntStream.rangeClosed(1, numberOfCalls)
        .mapToObj(i -> graphQlService.doBenchmarkCall(schema))
        .forEach(graphQlService::saveResult);
  }
}
