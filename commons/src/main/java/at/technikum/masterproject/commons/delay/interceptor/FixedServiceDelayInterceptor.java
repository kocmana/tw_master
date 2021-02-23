package at.technikum.masterproject.commons.delay.interceptor;

import at.technikum.masterproject.commons.delay.annotation.FixedEndpointDelaySimulation;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
@Slf4j
public class FixedServiceDelayInterceptor implements HandlerInterceptor {

  @Override
  @FixedEndpointDelaySimulation(delayInMs = 100)
  public boolean preHandle(final HttpServletRequest request, final HttpServletResponse response, final Object handler) {
    return true;
  }

}
