package at.technikum.masterproject.integrationservice.model.customer;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum Gender {
  MALE, FEMALE;

  @JsonValue
  public String encode(){
    return name().toLowerCase();
  }

  @JsonCreator
  public static Gender decode(String input){
    try {
      return Gender.valueOf(input.toUpperCase());
    } catch (Exception exception) {
      throw new IllegalArgumentException("Illegal argument provided for gender, please use male or female.");
    }
  }
}
