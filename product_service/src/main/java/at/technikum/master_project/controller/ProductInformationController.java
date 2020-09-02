package at.technikum.master_project.controller;

import java.util.Arrays;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductInformationController {

  @GetMapping(value = "/products")
  public List<String> getAllProducts(){
    return Arrays.asList("Product1", "Product2");
  }
}
