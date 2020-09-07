package at.technikum.masterproject.productinformation.model.dto;

import at.technikum.masterproject.productinformation.model.ProductDimension;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDto {

  private int id;
  private String name;
  private String description;
  private float weight;
  private ProductDimension dimension;
}
