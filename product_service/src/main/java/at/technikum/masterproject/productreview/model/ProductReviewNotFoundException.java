package at.technikum.masterproject.productreview.model;

public class ProductReviewNotFoundException extends RuntimeException {

  public ProductReviewNotFoundException() {
    super();
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
