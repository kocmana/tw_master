package at.technikum.masterproject.benchmarkservice.model.dto;

import at.technikum.masterproject.benchmarkservice.model.QueryStatistic;
import java.util.List;
import java.util.Map;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class BenchmarkResult {

  String schema;
  boolean finished;
  long numberOfCalls;
  double average;
  double min;
  double max;
  double total;
  Map<Long, Long> responseTimeDistribution;
  List<QueryStatistic> singleCallResults;
}
