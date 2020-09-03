package at.technikum.master_project.productinformation;

import static org.springframework.http.HttpStatus.NOT_FOUND;

import at.technikum.master_project.model.ErrorResponse;
import at.technikum.master_project.productinformation.model.ProductInformationNotFoundException;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class ProductInformationControllerAdvice {

  private static final String UUID_ATTRIBUTE = "uuid";

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
