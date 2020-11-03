package at.technikum.masterproject.productservice.productinformation.model.dto;

import javax.validation.constraints.NotBlank;
import lombok.Value;
import org.hibernate.validator.constraints.Length;

@Value
public class ProductCreationRequest {

  @NotBlank @Length(max = 255)
  String name;
  @NotBlank @Length(max = 500)
  String description;
  float weight;
  ProductDimensionDto dimension;
}
