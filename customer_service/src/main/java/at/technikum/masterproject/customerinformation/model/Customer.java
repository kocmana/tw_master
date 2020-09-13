package at.technikum.masterproject.customerinformation.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "customer_id")
  private Integer customerId;
  @Enumerated(EnumType.STRING)
  private Gender gender;
  @Column(name = "first_name")
  private String firstName;
  @Column(name = "last_name")
  private String lastName;
  private String street;
  @Column(name = "house_number")
  private String houseNumber;
  private String top;
  @Column(name = "postal_code")
  private String postalCode;
  private String country;
  @Column(name = "telephone_number")
  private String telephoneNumber;
  @Column(name = "email_address")
  private String emailAddress;
}
