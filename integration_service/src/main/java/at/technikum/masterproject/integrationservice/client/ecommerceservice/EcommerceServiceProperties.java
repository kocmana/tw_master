package at.technikum.masterproject.integrationservice.client.ecommerceservice;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@ConfigurationProperties(value = "services.ecommerceservice")
@ConstructorBinding
@AllArgsConstructor
@Getter
public class EcommerceServiceProperties {

  private final String url;
  private final int port;
  private final String username;
  private final String password;
}
