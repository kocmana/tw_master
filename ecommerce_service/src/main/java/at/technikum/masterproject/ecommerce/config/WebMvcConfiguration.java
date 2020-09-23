package at.technikum.masterproject.ecommerce.config;

import at.technikum.masterproject.commons.delay.interceptor.NormallyDistributedServiceDelayInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer {

  private final NormallyDistributedServiceDelayInterceptor normallyDistributedServiceDelayInterceptor;

  @Autowired
  public WebMvcConfiguration(NormallyDistributedServiceDelayInterceptor normallyDistributedServiceDelayInterceptor) {
    this.normallyDistributedServiceDelayInterceptor = normallyDistributedServiceDelayInterceptor;
  }

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(normallyDistributedServiceDelayInterceptor);
  }
}
