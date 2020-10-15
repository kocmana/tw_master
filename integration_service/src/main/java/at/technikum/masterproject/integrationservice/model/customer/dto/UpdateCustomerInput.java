package at.technikum.masterproject.integrationservice.model.customer.dto;

import at.technikum.masterproject.integrationservice.model.customer.Gender;
import lombok.Value;

@Value
public class UpdateCustomerInput {

  Integer id;
  Gender gender;
  String firstName;
  String lastName;
  String street;
  String houseNumber;
  String top;
  String postalCode;
  String country;
  String telephoneNumber;
  String emailAddress;
}
