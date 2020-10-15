package at.technikum.masterproject.customerservice.customerinformation.model.dto;

import at.technikum.masterproject.customerservice.customerinformation.model.Gender;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CustomerRegistrationRequest {

  private Gender gender;
  @NotBlank
  private String firstName;
  @NotBlank
  private String lastName;
  private String street;
  private String houseNumber;
  private String top;
  private String postalCode;
  @NotBlank
  private String country;
  private String telephoneNumber;
  @NotBlank
  @Email
  private String emailAddress;
}
