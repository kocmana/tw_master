package at.technikum.masterproject.ecommerce.config;

import at.technikum.masterproject.commons.delay.interceptor.NormallyDistributedServiceDelayInterceptor;
import at.technikum.masterproject.commons.logging.RequestLoggingInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@ComponentScan("at.technikum.masterproject.commons.logging")
public class WebMvcConfiguration implements WebMvcConfigurer {

  private final RequestLoggingInterceptor requestLoggingInterceptor;
  private final NormallyDistributedServiceDelayInterceptor normallyDistributedServiceDelayInterceptor;

  @Autowired
  public WebMvcConfiguration(
      RequestLoggingInterceptor requestLoggingInterceptor,
      NormallyDistributedServiceDelayInterceptor normallyDistributedServiceDelayInterceptor) {
    this.requestLoggingInterceptor = requestLoggingInterceptor;
    this.normallyDistributedServiceDelayInterceptor = normallyDistributedServiceDelayInterceptor;
  }

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(requestLoggingInterceptor);
    registry.addInterceptor(normallyDistributedServiceDelayInterceptor);
  }
}
