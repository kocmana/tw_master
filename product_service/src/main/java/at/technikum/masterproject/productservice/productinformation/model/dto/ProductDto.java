package at.technikum.masterproject.productservice.productinformation.model.dto;

import at.technikum.masterproject.productservice.productinformation.model.ProductDimension;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
@NoArgsConstructor
public class ProductDto {

  private int id;
  @Length(max = 255)
  private String name;
  @Length(max = 500)
  private String description;
  private float weight;
  private ProductDimension dimension;
}
