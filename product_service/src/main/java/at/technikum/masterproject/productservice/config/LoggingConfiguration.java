package at.technikum.masterproject.productservice.config;

import at.technikum.masterproject.commons.requestid.RequestIdInterceptor;
import at.technikum.masterproject.commons.requestlogging.EnableRequestLogging;
import java.util.UUID;
import java.util.function.Supplier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableRequestLogging
public class LoggingConfiguration {

  @Bean
  public RequestIdInterceptor<UUID> uuidRequestIdInterceptor() {
    Supplier<UUID> idGeneratorFunction = UUID::randomUUID;
    return new RequestIdInterceptor<>(idGeneratorFunction);
  }

}

