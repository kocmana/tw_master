package at.technikum.masterproject.integrationservice.timeout;

public class QueryTimeoutException extends RuntimeException {

  public QueryTimeoutException() {
  }

  public QueryTimeoutException(String message) {
    super(message);
  }

  public QueryTimeoutException(String message, Throwable cause) {
    super(message, cause);
  }
}
