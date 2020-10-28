package at.technikum.masterproject.benchmarkservice.service;

import at.technikum.masterproject.benchmarkservice.model.Customer;
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
  //private final Class<T> responseClass;


  public GraphQlService(GraphQLWebClient graphQlWebClient,
      QueryStatisticsRepository queryStatisticsRepository) {
    this.graphQlWebClient = graphQlWebClient;
    this.queryStatisticsRepository = queryStatisticsRepository;
    //this.responseClass = ((Class<T>) ((ParameterizedType) getClass()
    //    .getGenericSuperclass()).getActualTypeArguments()[0]);
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

  public void saveResult(QueryStatistic queryStatistic) {
    queryStatisticsRepository.save(queryStatistic);
    log.info(String.valueOf(queryStatisticsRepository.count()));
  }
}
