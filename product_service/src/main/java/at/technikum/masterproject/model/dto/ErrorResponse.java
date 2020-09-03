package at.technikum.masterproject.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ErrorResponse {

  private final String requestUuid;
  private final String errorMessage;
}
