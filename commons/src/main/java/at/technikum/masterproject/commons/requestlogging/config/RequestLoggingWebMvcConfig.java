package at.technikum.masterproject.commons.requestlogging.config;

import at.technikum.masterproject.commons.requestlogging.interceptor.RequestLoggingInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@Aspect
@Slf4j
public class RequestLoggingWebMvcConfig implements WebMvcConfigurer {

  private final RequestLoggingInterceptor requestLoggingInterceptor;

  @Autowired
  public RequestLoggingWebMvcConfig(RequestLoggingInterceptor requestLoggingInterceptor) {
    this.requestLoggingInterceptor = requestLoggingInterceptor;
  }

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(requestLoggingInterceptor);
  }
}
