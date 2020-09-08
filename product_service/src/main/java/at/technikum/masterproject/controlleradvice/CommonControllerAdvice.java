package at.technikum.masterproject.controlleradvice;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

import at.technikum.masterproject.model.error.ErrorResponse;
import at.technikum.masterproject.productinformation.model.ProductInformationNotFoundException;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import javax.validation.ValidationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class CommonControllerAdvice {

  private static final String UUID_ATTRIBUTE = "uuid";

  @ExceptionHandler(MethodArgumentNotValidException.class)
  ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(final MethodArgumentNotValidException ex,
      final HttpServletRequest request) {
    log.warn(ex.getMessage());

    return ResponseEntity
        .status(BAD_REQUEST)
        .body(new ErrorResponse(retrieveRequestUuid(request), ex.getMessage()));
  }

  @ExceptionHandler(ValidationException.class)
  ResponseEntity<ErrorResponse> handleValidationException(final ValidationException ex,
      final HttpServletRequest request) {
    log.warn(ex.getMessage());

    return ResponseEntity
        .status(BAD_REQUEST)
        .body(new ErrorResponse(retrieveRequestUuid(request), ex.getMessage()));
  }

  @ExceptionHandler(HttpMessageNotReadableException.class)
  ResponseEntity<ErrorResponse> handleMessageNotReadableException(final HttpMessageNotReadableException ex,
      final HttpServletRequest request) {
    log.warn(ex.getMessage());

    return ResponseEntity
        .status(BAD_REQUEST)
        .body(new ErrorResponse(retrieveRequestUuid(request), ex.getMessage()));
  }

  @ExceptionHandler(ProductInformationNotFoundException.class)
  ResponseEntity<ErrorResponse> handleProductInformationNotFoundException(final ProductInformationNotFoundException ex,
      final HttpServletRequest request) {
    log.warn(ex.getMessage());

    return ResponseEntity
        .status(NOT_FOUND)
        .body(new ErrorResponse(retrieveRequestUuid(request), ex.getMessage()));
  }

  private String retrieveRequestUuid(final HttpServletRequest request) {
    return Optional.ofNullable(request.getAttribute(UUID_ATTRIBUTE))
        .filter(String.class::isInstance)
        .map(String.class::cast)
        .orElse("No UUID defined.");
  }
}
