package at.technikum.masterproject.customerservice.config;

import at.technikum.masterproject.commons.requestid.RequestIdInterceptor;
import at.technikum.masterproject.commons.requestlogging.EnableRequestLogging;
import java.time.Instant;
import java.util.function.Supplier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableRequestLogging
public class LoggingConfiguration {

  @Bean
  public RequestIdInterceptor<Long> epochRequestIdInterceptor() {
    Supplier<Long> idGeneratorFunction = () -> Instant.now().getEpochSecond();
    return new RequestIdInterceptor<>(idGeneratorFunction);
  }

}

