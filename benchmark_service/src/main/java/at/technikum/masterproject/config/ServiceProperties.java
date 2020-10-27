package at.technikum.masterproject.config;

import lombok.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@ConstructorBinding
@ConfigurationProperties(prefix = "service")
@Value
public class ServiceProperties {

  String url = "localhost";
  int port = 8080;
  String endpoint = "/graphql";
  String username;
  String password;
}
