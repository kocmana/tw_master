package at.technikum.masterproject.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ErrorResponse {

  private final String requestUuid;
  private final String errorMessage;
}
