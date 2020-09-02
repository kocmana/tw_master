package at.technikum.master_project.productinformation;

import static org.springframework.http.HttpStatus.NOT_FOUND;

import at.technikum.master_project.productinformation.model.ProductInformationNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class ProductInformationControllerAdvice {

  @ExceptionHandler(ProductInformationNotFoundException.class)
  ResponseEntity<String> handleProductInformationNotFoundException(ProductInformationNotFoundException ex,
      WebRequest request) {
    return ResponseEntity
        .status(NOT_FOUND)
        .body("Product not found");
  }
}
