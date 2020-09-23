package at.technikum.masterproject.config;

import at.technikum.masterproject.commons.delay.interceptor.NormallyDistributedServiceDelayInterceptor;
import at.technikum.masterproject.commons.logging.RequestLoggingInterceptor;
import at.technikum.masterproject.interceptor.UuidInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer {

  private final UuidInterceptor uuidInterceptor;
  private final RequestLoggingInterceptor requestLoggingInterceptor;
  private final NormallyDistributedServiceDelayInterceptor normallyDistributedServiceDelayInterceptor;

  @Autowired
  public WebMvcConfiguration(UuidInterceptor uuidInterceptor,
      RequestLoggingInterceptor requestLoggingInterceptor,
      NormallyDistributedServiceDelayInterceptor normallyDistributedServiceDelayInterceptor) {
    this.uuidInterceptor = uuidInterceptor;
    this.requestLoggingInterceptor = requestLoggingInterceptor;
    this.normallyDistributedServiceDelayInterceptor = normallyDistributedServiceDelayInterceptor;
  }

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(uuidInterceptor);
    registry.addInterceptor(requestLoggingInterceptor);
    registry.addInterceptor(normallyDistributedServiceDelayInterceptor);
  }
}
