package at.technikum.masterproject.ecommerce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class EcommerceService {

  public static void main(String[] args) {
    SpringApplication.run(EcommerceService.class, args);
  }
}
