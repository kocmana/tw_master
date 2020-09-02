package at.technikum.master_project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableWebMvc()
public class ProductService
{
    public static void main( String[] args )
    {
        SpringApplication.run(ProductService.class, args);
    }
}
