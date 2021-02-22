package at.technikum.masterproject.customerservice.customerinformation.model.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum GenderDto {
  MALE, FEMALE;

  @JsonValue
  public String encode(){
    return name().toLowerCase();
  }

  @JsonCreator
  public static GenderDto decode(String input){
    try {
      return GenderDto.valueOf(input.toUpperCase());
    } catch (Exception exception) {
      throw new IllegalArgumentException("Illegal argument provided for gender, please use male or female.");
    }
  }
}