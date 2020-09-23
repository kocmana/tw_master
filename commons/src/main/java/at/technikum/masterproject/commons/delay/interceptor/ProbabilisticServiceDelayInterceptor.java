package at.technikum.masterproject.commons.delay.interceptor;

import at.technikum.masterproject.commons.delay.annotation.ProbabilisticEndpointDelay;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

@Component
@Slf4j
public class ProbabilisticServiceDelayInterceptor extends HandlerInterceptorAdapter {

  @Override
  @ProbabilisticEndpointDelay(probability = 50, duration = 100)
  public boolean preHandle(final HttpServletRequest request, final HttpServletResponse response, final Object handler) {
    return true;
  }

}
