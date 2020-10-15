package at.technikum.masterproject.integrationservice.model.product;

public class ProductServiceException extends RuntimeException {

  public ProductServiceException() {
    super();
  }

  public ProductServiceException(String message) {
    super(message);
  }

  public ProductServiceException(String message, Throwable cause) {
    super(message, cause);
  }
}
