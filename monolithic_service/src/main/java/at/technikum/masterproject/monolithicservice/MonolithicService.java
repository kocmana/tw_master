package at.technikum.masterproject.monolithicservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class MonolithicService {

  public static void main(String[] args) {
    SpringApplication.run(MonolithicService.class, args);
  }
}
