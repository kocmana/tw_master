package at.technikum.masterproject.integrationservice.client.productservice;

import at.technikum.masterproject.integrationservice.client.ServiceProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@ConfigurationProperties(value = "services.productservice", ignoreInvalidFields = true)
@ConstructorBinding
@AllArgsConstructor
@Getter
public class ProductServiceProperties extends ServiceProperties {

}
