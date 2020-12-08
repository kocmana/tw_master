package at.technikum.masterproject.integrationservice.logging;

import at.technikum.masterproject.integrationservice.logging.model.LoggingInstrumentationState;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.client.RestTemplateCustomizer;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.web.client.RestTemplate;

@Configuration
@Slf4j
@RequiredArgsConstructor
public class RestTemplateLoggingInterceptorCustomizer implements RestTemplateCustomizer {

  private final LoggingInstrumentationState loggingInstrumentationState;

  @Override
  public void customize(RestTemplate restTemplate) {

    List<ClientHttpRequestInterceptor> interceptors = restTemplate.getInterceptors();

    if (interceptors.isEmpty()) {
      interceptors = new ArrayList<>();
    }
    interceptors.add(new RestTemplateRequestIdInterceptor(loggingInstrumentationState));

    restTemplate.setInterceptors(interceptors);
  }

}
