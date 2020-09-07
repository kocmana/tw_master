package at.technikum.masterproject;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableWebMvc()
public class IntegrationService
{
    public static void main(String[] args) {
        SpringApplication.run(IntegrationService.class, args);
    }
}
