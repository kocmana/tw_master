package at.technikum.masterproject.service;

import graphql.schema.DataFetcher;
import org.springframework.stereotype.Component;

@Component
public class GraphQLDataFetchers {

  public DataFetcher getProductInformationByIdDataFetcher() {
    return dataFetchingEnvironment -> {
      return null;
    };
  }
}
