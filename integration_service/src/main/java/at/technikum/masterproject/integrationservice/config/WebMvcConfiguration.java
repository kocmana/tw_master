package at.technikum.masterproject.integrationservice.config;

import at.technikum.masterproject.commons.requestid.RequestIdInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer {

  private final RequestIdInterceptor<?> requestIdInterceptor;

  @Autowired
  public WebMvcConfiguration(RequestIdInterceptor<?> requestIdInterceptor) {
    this.requestIdInterceptor = requestIdInterceptor;
  }

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(requestIdInterceptor);
  }
}
