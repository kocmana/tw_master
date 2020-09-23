package at.technikum.masterproject.commons.delay.config;

import at.technikum.masterproject.commons.delay.interceptor.NormallyDistributedServiceDelayInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@Aspect
@ConditionalOnProperty(prefix = "service.delay", name = "enable-service-delays",
    havingValue = "true")
@Slf4j
public class DelayWebMvcConfig implements WebMvcConfigurer {

  private final NormallyDistributedServiceDelayInterceptor normallyDistributedServiceDelayInterceptor;

  @Autowired
  public DelayWebMvcConfig(
      NormallyDistributedServiceDelayInterceptor normallyDistributedServiceDelayInterceptor) {
    this.normallyDistributedServiceDelayInterceptor = normallyDistributedServiceDelayInterceptor;
    log.info("Service delays active.");
  }

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(normallyDistributedServiceDelayInterceptor);
  }
}
