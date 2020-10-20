package at.technikum.masterproject.service;

import at.technikum.masterproject.model.Customer;
import graphql.kickstart.spring.webclient.boot.GraphQLWebClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class GraphQLCaller {

  private GraphQLWebClient graphQLWebClient;

  @Autowired
  public GraphQLCaller(GraphQLWebClient graphQLWebClient) {
    this.graphQLWebClient = graphQLWebClient;
    callSomeEndpoint();
  }

  public void callSomeEndpoint() {
    Customer customer = graphQLWebClient.post("schemas/benchmark.graphqls", Customer.class).block();
    log.info(customer.toString());
  }
}
