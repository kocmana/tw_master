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

  @Override
  public boolean preHandle(HttpServletRequest request, final HttpServletResponse response, final Object handler) {
    UUID requestUuid = UUID.randomUUID();
    setUuidInMdcContext(requestUuid);
    setUuidAsRequestAttribute(request, requestUuid);
    return true;
  }

  @Override
  public void afterCompletion(final HttpServletRequest request, final HttpServletResponse response,
      final Object handler, final Exception ex) {
    request.removeAttribute("uuid");
  }

  private void setUuidInMdcContext(final UUID requestUuid) {
    MDC.put(UUID_KEY, requestUuid.toString());
  }

  private void setUuidAsRequestAttribute(HttpServletRequest request, final UUID requestUuid) {
    request.setAttribute(UUID_KEY, requestUuid.toString());
  }

}
