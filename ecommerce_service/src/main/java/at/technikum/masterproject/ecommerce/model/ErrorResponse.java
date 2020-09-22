package at.technikum.masterproject.ecommerce.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ErrorResponse {

  private final String errorMessage;
}
