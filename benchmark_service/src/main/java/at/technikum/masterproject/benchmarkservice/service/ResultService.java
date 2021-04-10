package at.technikum.masterproject.benchmarkservice.service;

import at.technikum.masterproject.benchmarkservice.model.Benchmark;
import at.technikum.masterproject.benchmarkservice.model.BenchmarkNotFoundException;
import at.technikum.masterproject.benchmarkservice.model.QueryStatistic;
import at.technikum.masterproject.benchmarkservice.model.dto.BenchmarkResult;
import at.technikum.masterproject.benchmarkservice.repository.BenchmarkRepository;
import at.technikum.masterproject.benchmarkservice.repository.QueryStatisticsRepository;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.stream.LongStream;
import org.apache.commons.math3.stat.descriptive.moment.StandardDeviation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ResultService {

  private final BenchmarkRepository benchmarkRepository;
  private final QueryStatisticsRepository queryStatisticsRepository;

  @Autowired
  public ResultService(
      BenchmarkRepository benchmarkRepository,
      QueryStatisticsRepository queryStatisticsRepository) {
    this.benchmarkRepository = benchmarkRepository;
    this.queryStatisticsRepository = queryStatisticsRepository;
  }

  public BenchmarkResult retrieveBenchmarkResults(String benchmarkUuid, int bucketSize) {

    Benchmark benchmark = benchmarkRepository.findById(benchmarkUuid)
        .orElseThrow(() -> (new BenchmarkNotFoundException(benchmarkUuid)));
    List<QueryStatistic> results = queryStatisticsRepository.findAllByBenchmarkUuid(benchmarkUuid);
    DoubleSummaryStatistics statistics = calculateStatistics(results);

    return BenchmarkResult.builder()
        .schema(benchmark.getSchema())
        .finished(benchmark.isFinished())
        .numberOfCalls(statistics.getCount())
        .average(statistics.getAverage())
        .standardDeviation(calculateStandardDeviation(results))
        .min(statistics.getMin())
        .max(statistics.getMax())
        .total(statistics.getSum())
        .responseTimeDistribution(
            calculateResponseTimeDistribution(results, bucketSize, statistics.getMin(), statistics.getMax()))
        .singleCallResults(results)
        .build();
  }

  private Double calculateStandardDeviation(List<QueryStatistic> result) {
    double[] responseTimes = result.stream()
        .map(QueryStatistic::getResponseTimeInMillis)
        .mapToDouble(Double::valueOf)
        .toArray();

    StandardDeviation standardDeviation = new StandardDeviation(true);
    return standardDeviation.evaluate(responseTimes);
  }

  private Map<Long, Long> calculateResponseTimeDistribution(List<QueryStatistic> results, long bucketSize,
      double min, double max) {
    TreeMap<Long, Long> groupedResults = results.stream()
        .collect(Collectors
            .groupingBy(queryResult -> (queryResult.getResponseTimeInMillis() / bucketSize),
                TreeMap::new,
                Collectors.counting()));
    LongStream.rangeClosed((long) (min / bucketSize), (long) (max / bucketSize))
        .forEach(x -> groupedResults.computeIfAbsent(x, key -> (0L)));
    return groupedResults;
  }

  private DoubleSummaryStatistics calculateStatistics(List<QueryStatistic> results) {
    return results.stream()
        .map(QueryStatistic::getResponseTimeInMillis)
        .mapToDouble(i -> i)
        .summaryStatistics();
  }
}
