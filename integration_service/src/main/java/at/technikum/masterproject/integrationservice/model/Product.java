package at.technikum.masterproject.integrationservice.model;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class Product {

  private int id;
  private String name;
  private String description;
  private float weight;
}
