package at.technikum.masterproject.commons.delay.interceptor;

import at.technikum.masterproject.commons.delay.annotation.NormallyDistributedEndpointDelay;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

@Component
@Slf4j
public class NormallyDistributedServiceDelayInterceptor extends HandlerInterceptorAdapter {

  @Override
  @NormallyDistributedEndpointDelay(mean = 100, standardDeviation = 50)
  public boolean preHandle(final HttpServletRequest request, final HttpServletResponse response, final Object handler) {
    return true;
  }

}
