package at.technikum.masterproject.productservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class ProductService {

  public static void main(String[] args) {
    SpringApplication.run(ProductService.class, args);
  }
}
