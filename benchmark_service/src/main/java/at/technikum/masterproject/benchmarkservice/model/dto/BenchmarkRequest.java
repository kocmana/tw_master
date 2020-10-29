package at.technikum.masterproject.benchmarkservice.model.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Value;

@Value
public class BenchmarkRequest {

  @NotBlank
  String schema;
  @NotNull @Min(1)
  Integer numberOfCalls;
}
