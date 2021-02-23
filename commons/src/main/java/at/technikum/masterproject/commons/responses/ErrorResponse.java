package at.technikum.masterproject.commons.responses;

import at.technikum.masterproject.commons.requestid.RequestId;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Getter;
import org.slf4j.MDC;

@Getter
public class ErrorResponse {

  @JsonInclude(Include.NON_NULL)
  String uuid;
  String errorMessage;

  protected ErrorResponse(String errorMessage) {
    this.uuid = retrieveRequestUuid();
    this.errorMessage = errorMessage;
  }

  public static ErrorResponse withMessage(String errorMessage) {
    return new ErrorResponse(errorMessage);
  }

  private String retrieveRequestUuid() {
    return MDC.get(RequestId.REQUEST_ID_KEY);
  }
}
