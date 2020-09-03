package at.technikum.master_project.config;

import at.technikum.master_project.interceptor.RequestLoggingInterceptor;
import at.technikum.master_project.interceptor.UuidInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer {

  private final UuidInterceptor uuidInterceptor;
  private final RequestLoggingInterceptor requestLoggingInterceptor;

  @Autowired
  public WebMvcConfiguration(UuidInterceptor uuidInterceptor,
      RequestLoggingInterceptor requestLoggingInterceptor) {
    this.uuidInterceptor = uuidInterceptor;
    this.requestLoggingInterceptor = requestLoggingInterceptor;
  }

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(uuidInterceptor);
    registry.addInterceptor(requestLoggingInterceptor);
  }
}
