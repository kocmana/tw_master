package at.technikum.masterproject.ecommerceservice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ErrorResponse {

  private final String errorMessage;
}
