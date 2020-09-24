package at.technikum.masterproject.commons.requestid;

import java.util.function.Supplier;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.MDC;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class RequestIdInterceptor<T> extends HandlerInterceptorAdapter {

  private static final String REQUEST_ID_KEY = "request-id";
  private static final String HEADER_NAME = "X-Request-ID";

  private final Supplier<T> idGenerator;

  public RequestIdInterceptor(Supplier<T> idGenerator) {
    this.idGenerator = idGenerator;
  }

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
      Object handler) {
    T requestId = idGenerator.get();
    setIdInMdcContext(requestId);
    setIdAsRequestAttribute(request, requestId);
    setIdAsResponseHeader(response, requestId);
    return true;
  }

  @Override
  public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
      Object handler, Exception ex) {
    request.removeAttribute(REQUEST_ID_KEY);
  }

  private void setIdInMdcContext(T requestId) {
    MDC.put(REQUEST_ID_KEY, requestId.toString());
  }

  private void setIdAsRequestAttribute(HttpServletRequest request, T requestId) {
    request.setAttribute(REQUEST_ID_KEY, requestId.toString());
  }

  private void setIdAsResponseHeader(HttpServletResponse response, T requestId) {
    response.addHeader(HEADER_NAME, requestId.toString());
  }

}
