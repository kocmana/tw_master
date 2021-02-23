package at.technikum.masterproject.commons.requestlogging.interceptor;

import at.technikum.masterproject.commons.requestlogging.RequestFields;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
@Slf4j
public class RequestLoggingInterceptor implements HandlerInterceptor {

  @Override
  public boolean preHandle(final HttpServletRequest request, final HttpServletResponse response, final Object handler) {
    addToMdcContext(request);
    logRequest(request);
    return true;
  }

  private void addToMdcContext(HttpServletRequest request) {
    MDC.put(RequestFields.REQUEST_METHOD, request.getMethod());
    MDC.put(RequestFields.REQUEST_URI, request.getRequestURI());
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
