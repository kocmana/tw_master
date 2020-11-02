package at.technikum.masterproject.integrationservice.client.ecommerceservice;

import at.technikum.masterproject.integrationservice.client.ServiceProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(value = "services.ecommerceservice", ignoreInvalidFields = true)
@Getter
@Setter
@AllArgsConstructor
public class EcommerceServiceProperties extends ServiceProperties {

}
