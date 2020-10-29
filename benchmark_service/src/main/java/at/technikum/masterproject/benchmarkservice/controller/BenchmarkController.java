package at.technikum.masterproject.benchmarkservice.controller;

import at.technikum.masterproject.benchmarkservice.model.Benchmark;
import at.technikum.masterproject.benchmarkservice.model.dto.BenchmarkRequest;
import at.technikum.masterproject.benchmarkservice.model.dto.ElementCreationResponse;
import at.technikum.masterproject.benchmarkservice.service.BenchmarkRunner;
import java.util.UUID;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/benchmark")
public class BenchmarkController {

  private final BenchmarkRunner benchmarkRunner;

  @Autowired
  public BenchmarkController(BenchmarkRunner benchmarkRunner) {
    this.benchmarkRunner = benchmarkRunner;
  }

  @PostMapping
  public ResponseEntity<ElementCreationResponse<UUID>> createBenchmark(
      @RequestBody @Valid BenchmarkRequest benchmarkRequest) {
    UUID requestUuid = UUID.randomUUID();
    Benchmark benchmark = Benchmark.builder()
        .schema(benchmarkRequest.getSchema())
        .numberOfCalls(benchmarkRequest.getNumberOfCalls())
        .uuid(requestUuid.toString())
        .build();
    benchmarkRunner.run(benchmark);
    return ResponseEntity.ok(new ElementCreationResponse<>(requestUuid));
  }

}
