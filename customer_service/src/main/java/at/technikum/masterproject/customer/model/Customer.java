package at.technikum.masterproject.customer.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "customer")
@Getter
@Setter
@NoArgsConstructor
public class Customer {

  @Id
  @GeneratedValue
  private int customerId;
  @Enumerated(EnumType.STRING)
  private Gender gender;
  private String firstName;
  private String lastName;
  private String street;
  private String door;
  private String top;
  private String postalCode;
  private String country;
  private String telephoneNumber;
  private String emailAddress;

}
