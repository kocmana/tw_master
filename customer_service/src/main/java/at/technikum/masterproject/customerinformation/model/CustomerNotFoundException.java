package at.technikum.masterproject.customerinformation.model;

public class CustomerNotFoundException extends RuntimeException {

  private static final String MESSAGE_TEMPLATE = "Can't find customer with Id: %d";

  public CustomerNotFoundException(String message) {
    super(message);
  }

  public CustomerNotFoundException(int customerId) {
    super(String.format(MESSAGE_TEMPLATE, customerId));
  }

  public CustomerNotFoundException(String message, Throwable cause) {
    super(message, cause);
  }
}
