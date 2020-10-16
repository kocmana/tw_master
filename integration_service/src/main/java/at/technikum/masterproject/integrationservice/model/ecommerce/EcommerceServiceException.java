package at.technikum.masterproject.integrationservice.model.ecommerce;

public class EcommerceServiceException extends RuntimeException {

  public EcommerceServiceException() {
  }

  public EcommerceServiceException(String message) {
    super(message);
  }

  public EcommerceServiceException(String message, Throwable cause) {
    super(message, cause);
  }
}
