package at.technikum.masterproject.benchmarkservice.service;

import at.technikum.masterproject.benchmarkservice.model.Benchmark;
import at.technikum.masterproject.benchmarkservice.model.QueryStatistic;
import at.technikum.masterproject.benchmarkservice.repository.QueryStatisticsRepository;
import graphql.kickstart.spring.webclient.boot.GraphQLWebClient;
import java.time.Instant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class GraphQlService {

  private final GraphQLWebClient graphQlWebClient;
  private final QueryStatisticsRepository queryStatisticsRepository;


  public GraphQlService(GraphQLWebClient graphQlWebClient,
      QueryStatisticsRepository queryStatisticsRepository) {
    this.graphQlWebClient = graphQlWebClient;
    this.queryStatisticsRepository = queryStatisticsRepository;
  }

  public QueryStatistic doBenchmarkCall(Benchmark benchmark) {
    Instant beforeCall = Instant.now();
    query(benchmark.getSchema(), Object.class);
    Instant afterCall = Instant.now();

    return QueryStatistic.builder()
        .benchmark(benchmark)
        .responseTimeInMillis(afterCall.toEpochMilli() - beforeCall.toEpochMilli())
        .build();
  }

  public <T> T query(String pathToSchemaResource, Class<T> responseClass) {
    return graphQlWebClient
        .post(pathToSchemaResource, responseClass)
        .block();
  }

  public void saveResult(QueryStatistic queryStatistic) {
    queryStatisticsRepository.save(queryStatistic);
  }
}
