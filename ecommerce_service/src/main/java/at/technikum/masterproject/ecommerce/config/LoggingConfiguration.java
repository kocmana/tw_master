package at.technikum.masterproject.ecommerce.config;

import at.technikum.masterproject.commons.requestid.RequestIdInterceptor;
import at.technikum.masterproject.commons.requestlogging.EnableRequestLogging;
import java.rmi.server.UID;
import java.util.function.Supplier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@EnableRequestLogging
@Configuration
public class LoggingConfiguration {

  @Bean
  public RequestIdInterceptor<UID> uidRequestIdInterceptor() {
    Supplier<UID> idGeneratorFunction = UID::new;
    return new RequestIdInterceptor<>(idGeneratorFunction);
  }

}
