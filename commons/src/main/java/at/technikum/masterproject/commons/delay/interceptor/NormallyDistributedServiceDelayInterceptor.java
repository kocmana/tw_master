package at.technikum.masterproject.commons.delay.interceptor;

import at.technikum.masterproject.commons.delay.config.DelayProperties;
import at.technikum.masterproject.commons.delay.model.NormallyDistributedDelay;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
@Slf4j
public class NormallyDistributedServiceDelayInterceptor implements HandlerInterceptor {

  private final NormallyDistributedDelay delay;
  private final DelayProperties delayProperties;

  @Autowired
  public NormallyDistributedServiceDelayInterceptor(
      DelayProperties delayProperties) {
    this.delayProperties = delayProperties;
    this.delay = new NormallyDistributedDelay(
        delayProperties.getServiceDelayMean(), delayProperties.getServiceDelayStandardDeviation());
  }

  @Override
  public boolean preHandle(final HttpServletRequest request, final HttpServletResponse response,
      final Object handler) {
    delayService();
    return true;
  }

  private void delayService() {
    if (delayProperties.isLogDelays()) {
      log.info("Simulating RTT: Delaying service call for {}ms...", delay.getDelayInMs());
      delay.delay();
    }
  }

}
