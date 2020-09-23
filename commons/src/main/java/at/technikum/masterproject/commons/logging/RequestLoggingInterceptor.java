package at.technikum.masterproject.commons.logging;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

@Component
@Slf4j
public class RequestLoggingInterceptor extends HandlerInterceptorAdapter {

  @Override
  public boolean preHandle(final HttpServletRequest request, final HttpServletResponse response, final Object handler) {
    logRequest(request);
    return true;
  }

  private void logRequest(final HttpServletRequest request) {
    log.info("Request received: {} {}",
        request.getMethod(),
        request.getRequestURI());
  }

  @Override
  public void afterCompletion(final HttpServletRequest request, final HttpServletResponse response,
      Object handler, Exception ex) {
    logResponse(request, response);
  }

  private void logResponse(final HttpServletRequest request, final HttpServletResponse response) {
    log.info("Request {} {} yielded {}",
        request.getMethod(), request.getRequestURI(),
        response.getStatus());
  }

}
