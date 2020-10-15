package at.technikum.masterproject.integrationservice.model.product.dto;

import at.technikum.masterproject.integrationservice.model.product.ProductDimension;
import lombok.Value;

@Value
public class CreateProductInput {

  String name;
  String description;
  Float weight;
  ProductDimension dimension;
}
