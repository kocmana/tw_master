package at.technikum.masterproject.ecommerce.purchase.model;

public class PurchaseNotFoundException extends RuntimeException {

  public PurchaseNotFoundException() {
  }

  public PurchaseNotFoundException(String message) {
    super(message);
  }

  public PurchaseNotFoundException(String message, Throwable cause) {
    super(message, cause);
  }

}
