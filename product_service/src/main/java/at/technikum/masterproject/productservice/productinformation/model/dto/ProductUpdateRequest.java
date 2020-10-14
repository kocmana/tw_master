package at.technikum.masterproject.productservice.productinformation.model.dto;

import at.technikum.masterproject.productservice.productinformation.model.ProductDimension;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Value;
import org.hibernate.validator.constraints.Length;

@Value
public class ProductUpdateRequest {

  @NotNull @Min(1)
  Integer id;
  @NotBlank @Length(max = 255)
  String name;
  @NotBlank @Length(max = 500)
  String description;
  float weight;
  ProductDimension dimension;
}
