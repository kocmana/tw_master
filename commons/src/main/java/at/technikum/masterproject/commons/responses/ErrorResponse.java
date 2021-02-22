package at.technikum.masterproject.commons.responses;

import lombok.Getter;

@Getter
public class ErrorResponse {

  protected ErrorResponse(String errorMessage) {
    this.errorMessage = errorMessage;
  }

  public static ErrorResponse withMessage(String errorMessage){
    return new ErrorResponse(errorMessage);
  }

  String errorMessage;
}
