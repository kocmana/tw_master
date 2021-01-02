package at.technikum.masterproject.integrationservice.logging;

import at.technikum.masterproject.integrationservice.logging.model.LoggingInstrumentationState;
import at.technikum.masterproject.integrationservice.timeout.MaxQueryDurationInstrumentation;
import graphql.execution.instrumentation.SimpleInstrumentation;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class InstrumentationConfiguration {

  @Bean
  SimpleInstrumentation loggingInstrumentation() {
    return new LoggingInstrumentation();
  }

  @Bean
  SimpleInstrumentation maxQueryDurationInstrumentation() {
    return new MaxQueryDurationInstrumentation();
  }

  @Bean
  @Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
  LoggingInstrumentationState loggingInstrumentationState() {
    return new LoggingInstrumentationState();
  }
}
