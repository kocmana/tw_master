package at.technikum.masterproject.productservice.productinformation.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ProductResponse {

  private int id;
  private String name;
  private String description;
  private float weight;
  private ProductDimensionDto dimension;
}
