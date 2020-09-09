package at.technikum.masterproject.controlleradvice;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

import javax.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class CommonControllerAdvice {

  private static final String GENERIC_ERROR_MESSAGE = "Could not handle request. An error occurred.";

  @ExceptionHandler(Exception.class)
  public ResponseEntity<String> handleGenericException(Exception ex, HttpServletRequest request) {
    log.error(generateLogEntry(request, ex));
    return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(GENERIC_ERROR_MESSAGE);
  }

  private String generateLogEntry(HttpServletRequest request, Exception ex) {
    return String.format("An error occurred while handling %s%s: %s",
        request.getMethod(), request.getRequestURI(), ex.getMessage());
  }

}
