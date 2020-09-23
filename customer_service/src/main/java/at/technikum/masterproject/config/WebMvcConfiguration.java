package at.technikum.masterproject.config;

import at.technikum.masterproject.commons.delay.interceptor.NormallyDistributedServiceDelayInterceptor;
import at.technikum.masterproject.commons.logging.RequestLoggingInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer {

  private final RequestLoggingInterceptor requestLoggingInterceptor;
  private final NormallyDistributedServiceDelayInterceptor normallyDistributedServiceDelayInterceptor;

  @Autowired
  public WebMvcConfiguration(NormallyDistributedServiceDelayInterceptor normallyDistributedServiceDelayInterceptor,
      RequestLoggingInterceptor requestLoggingInterceptor) {
    this.normallyDistributedServiceDelayInterceptor = normallyDistributedServiceDelayInterceptor;
    this.requestLoggingInterceptor = requestLoggingInterceptor;
  }

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(requestLoggingInterceptor);
    registry.addInterceptor(normallyDistributedServiceDelayInterceptor);
  }
}
