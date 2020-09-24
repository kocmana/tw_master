package at.technikum.masterproject.customerservice.controlleradvice;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;

import at.technikum.masterproject.customerservice.customerinformation.model.CustomerNotFoundException;
import javax.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class CommonControllerAdvice {

  private static final String GENERIC_ERROR_MESSAGE = "Could not handle request. An error occurred.";

  @ExceptionHandler(CustomerNotFoundException.class)
  public ResponseEntity<String> handleCustomerNotFoundException(CustomerNotFoundException ex,
      HttpServletRequest request) {
    log.warn("Requested Customer was not found.");

    return ResponseEntity
        .status(NOT_FOUND)
        .body("Customer not found");
  }

  @ExceptionHandler(BadCredentialsException.class)
  public ResponseEntity<String> handleBadCredentialsException(BadCredentialsException ex,
      HttpServletRequest request) {
    log.warn("Invalid Credentials were used.");

    return ResponseEntity
        .status(NOT_FOUND)
        .body("Invalid Credentials.");
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<String> handleGenericException(Exception ex, HttpServletRequest request) {
    log.error(generateLogEntry(request, ex));
    return ResponseEntity
        .status(INTERNAL_SERVER_ERROR)
        .body(GENERIC_ERROR_MESSAGE);
  }

  private String generateLogEntry(HttpServletRequest request, Exception ex) {
    return String.format("An error occurred while handling %s%s: %s",
        request.getMethod(), request.getRequestURI(), ex.getMessage());
  }

}
