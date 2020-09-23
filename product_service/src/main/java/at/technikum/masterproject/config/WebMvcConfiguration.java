package at.technikum.masterproject.config;

import at.technikum.masterproject.commons.delay.interceptor.NormallyDistributedServiceDelayInterceptor;
import at.technikum.masterproject.commons.requestlogging.interceptor.RequestLoggingInterceptor;
import at.technikum.masterproject.interceptor.UuidInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer {

  private final UuidInterceptor uuidInterceptor;

  @Autowired
  public WebMvcConfiguration(UuidInterceptor uuidInterceptor,
      RequestLoggingInterceptor requestLoggingInterceptor,
      NormallyDistributedServiceDelayInterceptor normallyDistributedServiceDelayInterceptor) {
    this.uuidInterceptor = uuidInterceptor;
  }

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(uuidInterceptor);
  }
}
