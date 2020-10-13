package at.technikum.masterproject.integrationservice.logging;

import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.client.RestTemplateCustomizer;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.web.client.RestTemplate;

@Configuration
@Slf4j
public class RestTemplateLoggingInterceptorCustomizer implements RestTemplateCustomizer {

  @Override
  public void customize(RestTemplate restTemplate) {
    List<ClientHttpRequestInterceptor> interceptors = restTemplate
        .getInterceptors();
    if (interceptors.isEmpty()) {
      interceptors = new ArrayList<ClientHttpRequestInterceptor>();
    }
    interceptors.add(new RestTemplateRequestIdInterceptor());
    restTemplate.setInterceptors(interceptors);
  }

}
