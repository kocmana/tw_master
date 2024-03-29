package at.technikum.masterproject.commons.responses;

import static java.util.stream.Collectors.toUnmodifiableList;

import java.util.List;
import lombok.Getter;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;

@Getter
public class ValidationErrorResponse extends ErrorResponse {

  private static final String VALIDATION_ERROR_MESSAGE = "Validation failed for the request.";

  private final List<String> validationErrorDetails;

  public static ValidationErrorResponse forException(MethodArgumentNotValidException exception) {
    return new ValidationErrorResponse(exception);
  }

  private ValidationErrorResponse(MethodArgumentNotValidException exception) {
    super(VALIDATION_ERROR_MESSAGE);
    this.validationErrorDetails = parseFieldErrors(exception.getAllErrors());
  }

  private List<String> parseFieldErrors(List<ObjectError> objectErrorList) {
    return objectErrorList.stream()
        .filter(element -> element instanceof FieldError)
        .map(FieldError.class::cast)
        .map(this::extractFieldError)
        .collect(toUnmodifiableList());
  }

  private String extractFieldError(FieldError fieldError) {
    return String.format("%s %s",
        fieldError.getField(),
        fieldError.getDefaultMessage());
  }
}
