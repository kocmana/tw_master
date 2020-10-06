package at.technikum.masterproject.integrationservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class IntegrationService {

  public static void main(String[] args) {
    SpringApplication.run(IntegrationService.class, args);
  }
}
