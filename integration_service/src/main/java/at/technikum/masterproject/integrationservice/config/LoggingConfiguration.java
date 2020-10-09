package at.technikum.masterproject.integrationservice.config;

import at.technikum.masterproject.commons.requestid.RequestIdInterceptor;
import at.technikum.masterproject.commons.requestlogging.EnableRequestLogging;
import java.util.UUID;
import java.util.function.Supplier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@EnableRequestLogging
@Configuration
public class LoggingConfiguration implements WebMvcConfigurer {

  @Bean
  public RequestIdInterceptor<UUID> uidRequestIdInterceptor() {
    Supplier<UUID> idGeneratorFunction = UUID::randomUUID;
    return new RequestIdInterceptor<>(idGeneratorFunction);
  }

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(uidRequestIdInterceptor());
  }

}
