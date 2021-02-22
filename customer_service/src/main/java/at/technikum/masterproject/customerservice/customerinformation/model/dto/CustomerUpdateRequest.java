package at.technikum.masterproject.customerservice.customerinformation.model.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class CustomerUpdateRequest {

  Integer customerId;
  GenderDto gender;
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
