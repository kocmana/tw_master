package at.technikum.masterproject.benchmarkservice.service;

import at.technikum.masterproject.benchmarkservice.model.BenchmarkResult;
import at.technikum.masterproject.benchmarkservice.model.QueryStatistic;
import at.technikum.masterproject.benchmarkservice.repository.QueryStatisticsRepository;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.stream.LongStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ResultService {

  private final QueryStatisticsRepository queryStatisticsRepository;

  @Autowired
  public ResultService(
      QueryStatisticsRepository queryStatisticsRepository) {
    this.queryStatisticsRepository = queryStatisticsRepository;
  }

  public List<QueryStatistic> retrieveStatistics(String schema) {
    return queryStatisticsRepository.findAllBySchema(schema);
  }

  public BenchmarkResult retrieveBenchmarkResults(String schema, int bucketSize) {
    List<QueryStatistic> results = queryStatisticsRepository.findAllBySchema(schema);
    DoubleSummaryStatistics statistics = calculateStatistics(results);

    return BenchmarkResult.builder()
        .schema(schema)
        .numberOfCalls(statistics.getCount())
        .average(statistics.getAverage())
        .min(statistics.getMin())
        .max(statistics.getMax())
        .total(statistics.getSum())
        .responseTimeDistribution(calculateResponseTimeDistribution(results, bucketSize, statistics.getMin(), statistics.getMax()))
        .singleCallResults(results)
        .build();
  }

  private Map<Long, Long> calculateResponseTimeDistribution(List<QueryStatistic> results, long bucketSize,
      double min, double max) {
    TreeMap<Long, Long> groupedResults = results.stream()
        .collect(Collectors
            .groupingBy(queryResult -> (queryResult.getResponseTimeInMillis() / bucketSize),
                TreeMap::new,
                Collectors.counting()));
    LongStream.rangeClosed((long)(min/bucketSize), (long)(max/bucketSize))
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
