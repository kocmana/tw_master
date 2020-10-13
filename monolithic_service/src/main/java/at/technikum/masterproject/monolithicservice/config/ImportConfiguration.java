package at.technikum.masterproject.monolithicservice.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@ComponentScan(basePackages = {
    "at.technikum.masterproject.customerservice.customerinformation",
    "at.technikum.masterproject.customerservice.customernetwork",
    "at.technikum.masterproject.ecommerceservice.price",
    "at.technikum.masterproject.ecommerceservice.purchase",
    "at.technikum.masterproject.productservice.productinformation",
    "at.technikum.masterproject.productservice.productreview"
})
@EnableJpaRepositories(basePackages = {
    "at.technikum.masterproject.customerservice.customerinformation",
    "at.technikum.masterproject.customerservice.customernetwork",
    "at.technikum.masterproject.ecommerceservice.price",
    "at.technikum.masterproject.ecommerceservice.purchase",
    "at.technikum.masterproject.productservice.productinformation",
    "at.technikum.masterproject.productservice.productreview"
})
@EntityScan(basePackages = {
    "at.technikum.masterproject.customerservice.customerinformation",
    "at.technikum.masterproject.customerservice.customernetwork",
    "at.technikum.masterproject.ecommerceservice.price",
    "at.technikum.masterproject.ecommerceservice.purchase",
    "at.technikum.masterproject.productservice.productinformation",
    "at.technikum.masterproject.productservice.productreview"
})
public class ImportConfiguration {

}
