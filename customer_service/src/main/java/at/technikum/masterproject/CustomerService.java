package at.technikum.masterproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableWebMvc
public class CustomerService {

  public static void main(String[] args) {
    SpringApplication.run(CustomerService.class, args);
  }
}
