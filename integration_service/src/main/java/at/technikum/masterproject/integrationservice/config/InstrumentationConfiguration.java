package at.technikum.masterproject.integrationservice.config;

import graphql.execution.instrumentation.SimpleInstrumentation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InstrumentationConfiguration {

  @Bean
  SimpleInstrumentation loggingInstrumentation(){
    return new LoggingInstrumentation();
  }
}
