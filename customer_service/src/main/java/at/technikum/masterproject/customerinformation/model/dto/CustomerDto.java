package at.technikum.masterproject.customerinformation.model.dto;

import at.technikum.masterproject.customerinformation.model.Gender;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CustomerDto {

  private Integer customerId;
  private Gender gender;
  @NotNull
  private String firstName;
  @NotNull
  private String lastName;
  private String street;
  private String door;
  private String top;
  private String postalCode;
  @NotNull
  private String country;
  private String telephoneNumber;
  @NotNull
  @Email
  private String emailAddress;
}
