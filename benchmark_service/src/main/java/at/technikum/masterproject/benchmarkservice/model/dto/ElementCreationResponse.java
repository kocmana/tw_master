package at.technikum.masterproject.benchmarkservice.model.dto;

import lombok.Value;

@Value
public class ElementCreationResponse<T> {

  T id;
}
