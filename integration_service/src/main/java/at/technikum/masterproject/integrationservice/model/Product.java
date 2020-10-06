package at.technikum.masterproject.integrationservice.model;

import lombok.Value;

@Value
public class Product {

  int id;
  String name;
  String description;
  float weight;
}
