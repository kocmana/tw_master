package at.technikum.masterproject.integrationservice.exceptionhandler;

import at.technikum.masterproject.integrationservice.timeout.QueryTimeoutException;
import graphql.kickstart.spring.error.ThrowableGraphQLError;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.HttpStatusCodeException;

@Component
@Slf4j
public class CommonExceptionHandler {

  @ExceptionHandler(HttpClientErrorException.NotFound.class)
  public ThrowableGraphQLError handleResourceNotFoundException(HttpClientErrorException exception) {
    return handleExceptionAndReturnGraphQlError(exception, "The requested resource was not found.");
  }

  @ExceptionHandler(HttpClientErrorException.BadRequest.class)
  public ThrowableGraphQLError handleBadRequestException(HttpClientErrorException exception) {
    return handleExceptionAndReturnGraphQlError(exception, "Bad request, please check input.");
  }

  @ExceptionHandler(HttpClientErrorException.Unauthorized.class)
  public ThrowableGraphQLError handleUnauthorizedException(HttpClientErrorException exception) {
    return handleExceptionAndReturnGraphQlError(exception,
        "You don't possess sufficient user rights to perform the requested action.");
  }

  @ExceptionHandler(HttpClientErrorException.class)
  public ThrowableGraphQLError handleGenericClientErrorException(HttpClientErrorException exception) {
    return handleExceptionAndReturnGraphQlError(exception, "Invalid request.");
  }

  @ExceptionHandler(HttpServerErrorException.class)
  public ThrowableGraphQLError handleServerErrorException(HttpServerErrorException exception) {
    return handleExceptionAndReturnGraphQlError(exception, "The server could not handle the request.");
  }


  @ExceptionHandler(QueryTimeoutException.class)
  public ThrowableGraphQLError handleQueryTimeoutException(QueryTimeoutException exception) {
    log.info("Request exceeded maximum duration.");
    return new ThrowableGraphQLError(exception,  "The request exceeded the maximum allowed timeframe.");
  }

  @ExceptionHandler(Exception.class)
  public ThrowableGraphQLError handleGenericException(Exception exception) {
    log.warn("Uncaught exception {} occurred during handling of request: {}",
        exception.getClass().getCanonicalName(), exception.getMessage(), exception);
    return new ThrowableGraphQLError(exception, "An error occurred.");
  }

  private ThrowableGraphQLError handleExceptionAndReturnGraphQlError(HttpStatusCodeException exception,
      String errorMessage) {
    log.warn("Downstream system returned {}: {}", exception.getStatusText(), exception.getMessage());
    return new ThrowableGraphQLError(exception, errorMessage);
  }

}
