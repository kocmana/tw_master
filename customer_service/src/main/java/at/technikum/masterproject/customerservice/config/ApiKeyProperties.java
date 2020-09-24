package at.technikum.masterproject.customerservice.config;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@ConstructorBinding
@ConfigurationProperties(prefix = "customerservice.api-key")
@AllArgsConstructor
@Getter
public class ApiKeyProperties {

  private final String header;
  private final List<String> values;
}


