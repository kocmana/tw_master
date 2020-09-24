package at.technikum.masterproject.productservice.productinformation.model;

public class ProductInformationNotFoundException extends RuntimeException {

  private static final String MESSAGE_TEMPLATE = "Product with product ID %d not found";

  public ProductInformationNotFoundException() {
    super();
  }

  public ProductInformationNotFoundException(Integer productId) {
    super(String.format(MESSAGE_TEMPLATE, productId));
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
