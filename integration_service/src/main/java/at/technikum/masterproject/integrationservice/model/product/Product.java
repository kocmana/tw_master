package at.technikum.masterproject.integrationservice.model.product;

import lombok.Value;

@Value
public class Product {

  int id;
  String name;
  String description;
  float weight;
  ProductDimension dimension;
}
