package at.technikum.masterproject.integrationservice.client.customerservice;

import at.technikum.masterproject.integrationservice.client.ServiceProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@ConfigurationProperties(value = "services.customerservice", ignoreInvalidFields = true)
@ConstructorBinding
@AllArgsConstructor
@Getter
public class CustomerServiceProperties extends ServiceProperties {

}
