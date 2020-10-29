package at.technikum.masterproject.customerservice.customerinformation.model.dto;

import at.technikum.masterproject.customerservice.customerinformation.model.Gender;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import lombok.Value;

@Value
public class CustomerUpdateRequest {

  Integer customerId;
  Gender gender;
  @NotBlank
  String firstName;
  @NotBlank
  String lastName;
  String street;
  String houseNumber;
  String top;
  String postalCode;
  @NotBlank
  String country;
  String telephoneNumber;
  @NotBlank
  @Email
  String emailAddress;
}
