package at.technikum.masterproject.benchmarkservice.model;

public class BenchmarkNotFoundException extends RuntimeException {

  private static final String MESSAGE_TEMPLATE = "Benchmark with ID %s not found";

  public BenchmarkNotFoundException(String benchmarkUuid) {
    super(String.format(MESSAGE_TEMPLATE, benchmarkUuid));
  }
}
