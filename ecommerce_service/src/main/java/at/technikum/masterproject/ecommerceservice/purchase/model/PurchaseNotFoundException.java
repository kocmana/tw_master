package at.technikum.masterproject.ecommerceservice.purchase.model;

public class PurchaseNotFoundException extends RuntimeException {

  private static final String MESSAGE_TEMPLATE = "Purchase with ID %d not found.";

  public PurchaseNotFoundException() {
  }

  public PurchaseNotFoundException(long purchaseId) {
    super(String.format(MESSAGE_TEMPLATE, purchaseId));
  }

  public PurchaseNotFoundException(String message) {
    super(message);
  }

  public PurchaseNotFoundException(String message, Throwable cause) {
    super(message, cause);
  }

}
