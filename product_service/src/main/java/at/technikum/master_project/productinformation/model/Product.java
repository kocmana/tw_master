package at.technikum.master_project.productinformation.model;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Product {

  @Id
  private int id;
  private String name;
  private String description;
  private float weight;
  @Embedded
  private ProductDimension dimension;
}
