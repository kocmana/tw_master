package at.technikum.masterproject.integrationservice.productservice;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@ConfigurationProperties(value = "services.productservice")
@ConstructorBinding
@AllArgsConstructor
@Getter
public class ProductServiceProperties {

  private final String url;
  private final int port;
  private final String username;
  private final String password;
}
