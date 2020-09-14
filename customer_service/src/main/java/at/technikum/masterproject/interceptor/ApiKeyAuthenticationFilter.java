package at.technikum.masterproject.interceptor;

import javax.servlet.http.HttpServletRequest;
import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;

public class ApiKeyAuthenticationFilter extends AbstractPreAuthenticatedProcessingFilter {

  private final String principalRequestHeader;

  public ApiKeyAuthenticationFilter(String principalRequestHeader) {
    this.principalRequestHeader = principalRequestHeader;
  }

  @Override
  protected Object getPreAuthenticatedPrincipal(HttpServletRequest request) {
    return request.getHeader(principalRequestHeader);
  }

  @Override
  protected Object getPreAuthenticatedCredentials(HttpServletRequest request) {
    return "N/A";
  }

}
