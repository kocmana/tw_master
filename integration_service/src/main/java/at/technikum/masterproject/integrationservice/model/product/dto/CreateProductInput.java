package at.technikum.masterproject.integrationservice.model.product.dto;

import lombok.Data;

@Data
public class CreateProductInput {

  String name;
  String description;
  Float weight;
  Float width;
  Float height;
  Float depth;
}
