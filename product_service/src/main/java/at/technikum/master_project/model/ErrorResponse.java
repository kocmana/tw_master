package at.technikum.master_project.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ErrorResponse {

  private final String requestUuid;
  private final String errorMessage;
}
