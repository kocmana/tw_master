package at.technikum.masterproject.productservice.model.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ErrorResponse {

  private final String requestUuid;
  private final String errorMessage;
}
