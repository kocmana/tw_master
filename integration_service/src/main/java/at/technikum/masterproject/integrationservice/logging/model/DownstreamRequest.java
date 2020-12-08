package at.technikum.masterproject.integrationservice.logging.model;

import java.net.URI;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;

@Builder
@Data
public class DownstreamRequest {

  private static final String TO_STRING_TEMPLATE = "%s %s (%s in %d ms): %s";

  private final HttpMethod method;
  private final URI endpoint;
  private final HttpStatus returnCode;
  private final String id;
  private final long duration;

  public final String toString() {
    return String.format(TO_STRING_TEMPLATE,
        method.toString(),
        endpoint.toString(),
        returnCode.toString(),
        duration,
        id);
  }
}
