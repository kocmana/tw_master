package at.technikum.masterproject.customerservice.customerinformation.model.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Customer {

  private Integer customerId;
  private Gender gender;
  private String firstName;
  private String lastName;
  private String street;
  private String houseNumber;
  private String top;
  private String postalCode;
  private String country;
  private String telephoneNumber;
  private String emailAddress;
}
