package at.technikum.masterproject.integrationservice.config;

import at.technikum.masterproject.integrationservice.logging.model.LoggingInstrumentationState;
import at.technikum.masterproject.integrationservice.resolver.dataloader.DataLoaderRegistryFactory;
import graphql.kickstart.execution.context.GraphQLContext;
import graphql.kickstart.servlet.context.DefaultGraphQLServletContext;
import graphql.kickstart.servlet.context.GraphQLServletContextBuilder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.Session;
import javax.websocket.server.HandshakeRequest;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.NotImplementedException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomGraphQlContextBuilder implements GraphQLServletContextBuilder {

  private final DataLoaderRegistryFactory dataLoaderRegistryFactory;
  private final LoggingInstrumentationState loggingInstrumentationState;

  @Override
  public GraphQLContext build(HttpServletRequest request, HttpServletResponse response) {

    DefaultGraphQLServletContext context = DefaultGraphQLServletContext.createServletContext()
        .with(request)
        .with(response)
        .with(dataLoaderRegistryFactory.create())
        .build();

    return new LoggingGraphQlContext(loggingInstrumentationState, context);
  }

  @Override
  public GraphQLContext build() {
    throw new NotImplementedException("Method not implemented.");
  }

  @Override
  public GraphQLContext build(Session session, HandshakeRequest handshakeRequest) {
    throw new NotImplementedException("Method not implemented.");
  }
}
