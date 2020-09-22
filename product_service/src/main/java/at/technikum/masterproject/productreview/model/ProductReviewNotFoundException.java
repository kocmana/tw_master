package at.technikum.masterproject.productreview.model;

public class ProductReviewNotFoundException extends RuntimeException {

  private static final String MESSAGE_TEMPLATE = "No product review with ID %d found.";

  public ProductReviewNotFoundException() {
    super();
  }

  public ProductReviewNotFoundException(int reviewId) {
    super(String.format(MESSAGE_TEMPLATE, reviewId));
  }

  public ProductReviewNotFoundException(String message) {
    super(message);
  }

  public ProductReviewNotFoundException(String message, Throwable cause) {
    super(message, cause);
  }

  public ProductReviewNotFoundException(Throwable cause) {
    super(cause);
  }
}
