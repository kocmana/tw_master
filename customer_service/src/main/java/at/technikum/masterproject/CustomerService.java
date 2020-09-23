package at.technikum.masterproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class CustomerService {

  public static void main(String[] args) {
    SpringApplication.run(CustomerService.class, args);
  }
}
