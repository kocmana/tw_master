package at.technikum.masterproject.monolithicservice.config;

import at.technikum.masterproject.commons.requestid.RequestIdInterceptor;
import at.technikum.masterproject.commons.requestlogging.EnableRequestLogging;
import java.util.UUID;
import java.util.function.Supplier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@EnableRequestLogging
@Configuration
public class LoggingConfiguration {

  @Bean
  public RequestIdInterceptor<UUID> uidRequestIdInterceptor() {
    Supplier<UUID> idGeneratorFunction = UUID::randomUUID;
    return new RequestIdInterceptor<>(idGeneratorFunction);
  }

}
