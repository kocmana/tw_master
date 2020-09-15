package at.technikum.masterproject.controller;

import static graphql.schema.idl.TypeRuntimeWiring.newTypeWiring;

import at.technikum.masterproject.service.GraphQlDataFetchers;
import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;
import java.io.IOException;
import java.net.URL;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class GraphQlProvider {

  private GraphQL graphQl;
  @Autowired
  private GraphQlDataFetchers graphQlDataFetchers;

  @Bean
  public GraphQL graphQl() {
    return graphQl;
  }

  @PostConstruct
  public void init() throws IOException {
    URL url = Resources.getResource("schema.graphqls");
    String sdl = Resources.toString(url, Charsets.UTF_8);
    GraphQLSchema graphQlSchema = buildSchema(sdl);
    this.graphQl = GraphQL.newGraphQL(graphQlSchema).build();
  }

  private GraphQLSchema buildSchema(String sdl) {
    TypeDefinitionRegistry typeRegistry = new SchemaParser().parse(sdl);
    RuntimeWiring runtimeWiring = buildWiring();
    SchemaGenerator schemaGenerator = new SchemaGenerator();
    return schemaGenerator.makeExecutableSchema(typeRegistry, runtimeWiring);
  }

  private RuntimeWiring buildWiring() {
    return RuntimeWiring.newRuntimeWiring()
        .type(newTypeWiring("Query")
            .dataFetcher("productInformationById",
                graphQlDataFetchers.getProductInformationByIdDataFetcher()))
        .build();
  }
}
