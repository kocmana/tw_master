package at.technikum.masterproject.productinformation.model;

public class ProductInformationNotFoundException extends RuntimeException {

  public ProductInformationNotFoundException() {
    super();
  }

  public ProductInformationNotFoundException(String message) {
    super(message);
  }

  public ProductInformationNotFoundException(String message, Throwable cause) {
    super(message, cause);
  }

  public ProductInformationNotFoundException(Throwable cause) {
    super(cause);
  }
}
