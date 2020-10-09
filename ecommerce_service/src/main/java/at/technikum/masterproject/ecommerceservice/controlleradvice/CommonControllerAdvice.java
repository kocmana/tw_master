package at.technikum.masterproject.ecommerceservice.controlleradvice;

import at.technikum.masterproject.ecommerceservice.model.ErrorResponse;
import at.technikum.masterproject.ecommerceservice.price.model.PriceNotFoundException;
import at.technikum.masterproject.ecommerceservice.purchase.model.PurchaseNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class CommonControllerAdvice {

  private static final String GENERIC_ERROR_MESSAGE = "An error has occurred.";

  @ExceptionHandler({PurchaseNotFoundException.class, PriceNotFoundException.class})
  ResponseEntity<ErrorResponse> handlePriceNotFoundException(Exception exception) {
    log.warn(exception.getMessage());

    return ResponseEntity
        .status(HttpStatus.NOT_FOUND)
        .body(new ErrorResponse(exception.getMessage()));
  }

  @ExceptionHandler(IllegalArgumentException.class)
  ResponseEntity<ErrorResponse> handleIllegalArgumentException(IllegalArgumentException exception) {
    log.warn(exception.getMessage());

    return ResponseEntity
        .status(HttpStatus.BAD_REQUEST)
        .body(new ErrorResponse(exception.getMessage()));
  }

  @ExceptionHandler(Exception.class)
  ResponseEntity<ErrorResponse> handleGenericException(Exception exception) {
    log.error("Caught unknown exception: {}, {}", exception.getMessage(), exception.getStackTrace());

    return ResponseEntity
        .status(HttpStatus.INTERNAL_SERVER_ERROR)
        .body(new ErrorResponse(GENERIC_ERROR_MESSAGE));
  }

}
