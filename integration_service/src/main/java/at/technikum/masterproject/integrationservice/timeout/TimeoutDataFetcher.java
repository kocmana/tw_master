package at.technikum.masterproject.integrationservice.timeout;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;

public class TimeoutDataFetcher<T> implements DataFetcher<T> {

  private static final String QUERY_TIMEOUT_MESSAGE = "Maximum query duration exceeded.";

  @Override
  public T get(DataFetchingEnvironment environment) throws Exception {
    throw new QueryTimeoutException(QUERY_TIMEOUT_MESSAGE);
  }
}
