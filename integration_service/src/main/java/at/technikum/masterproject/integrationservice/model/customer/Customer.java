package at.technikum.masterproject.integrationservice.model.customer;

import lombok.Value;

@Value
public class Customer {

  Integer customerId;
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
