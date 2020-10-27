package at.technikum.masterproject.service;

import at.technikum.masterproject.model.Customer;
import at.technikum.masterproject.model.QueryStatistic;
import at.technikum.masterproject.repository.QueryStatisticsRepository;
import graphql.kickstart.spring.webclient.boot.GraphQLWebClient;
import java.time.Instant;
import java.util.List;
import java.util.stream.IntStream;
import javax.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class GraphQlCaller {

  private final GraphQLWebClient graphQlWebClient;
  private final QueryStatisticsRepository queryStatisticsRepository;
  //private final Class<T> responseClass;


  public GraphQlCaller(GraphQLWebClient graphQlWebClient,
      QueryStatisticsRepository queryStatisticsRepository) {
    this.graphQlWebClient = graphQlWebClient;
    this.queryStatisticsRepository = queryStatisticsRepository;
    //this.responseClass = ((Class<T>) ((ParameterizedType) getClass()
    //    .getGenericSuperclass()).getActualTypeArguments()[0]);
  }

  @PostConstruct
  public void start(){
    doBenchmark("schemas/benchmark.graphqls", 20);
  }

  public void doBenchmark(String schema, int numberOfCalls) {
    IntStream.rangeClosed(1, numberOfCalls)
        .mapToObj((moo)->doBenchmarkCall(schema))
        .forEach(this::saveResult);
  }

  public List<QueryStatistic> retrieveStatistics(String schema){
    return queryStatisticsRepository.findAllBySchema(schema);
  }


  public QueryStatistic doBenchmarkCall(String schema) {
    Instant beforeCall = Instant.now();
    query(schema, Customer.class);
    Instant afterCall = Instant.now();

    return QueryStatistic.builder()
        .schema(schema)
        .responseTimeInMillis(afterCall.toEpochMilli() - beforeCall.toEpochMilli())
        .build();
  }

  public <T> T query(String pathToSchemaResource, Class<T> responseClass) {
    return graphQlWebClient
        .post(pathToSchemaResource, responseClass)
        .block();
  }

  private void saveResult(QueryStatistic queryStatistic) {
    queryStatisticsRepository.save(queryStatistic);
    log.info(String.valueOf(queryStatisticsRepository.count()));
  }
}
