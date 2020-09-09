package at.technikum.masterproject.interceptor;

import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

@Component
public class UuidInterceptor extends HandlerInterceptorAdapter {

  private static final String UUID_KEY = "uuid";
  private static final String HEADER_NAME = "X-Request-ID";

  @Override
  public boolean preHandle(HttpServletRequest request, final HttpServletResponse response, final Object handler) {
    UUID requestUuid = UUID.randomUUID();
    setUuidInMdcContext(requestUuid);
    setUuidAsRequestAttribute(request, requestUuid);
    setUuidAsResponseHeader(response, requestUuid);
    return true;
  }

  @Override
  public void afterCompletion(final HttpServletRequest request, final HttpServletResponse response,
      final Object handler, final Exception ex) {
    request.removeAttribute(UUID_KEY);
  }

  private void setUuidInMdcContext(final UUID requestUuid) {
    MDC.put(UUID_KEY, requestUuid.toString());
  }

  private void setUuidAsRequestAttribute(HttpServletRequest request, final UUID requestUuid) {
    request.setAttribute(UUID_KEY, requestUuid.toString());
  }

  private void setUuidAsResponseHeader(HttpServletResponse response, UUID requestUuid) {
    response.addHeader(HEADER_NAME, requestUuid.toString());
  }

}
