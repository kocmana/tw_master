package at.technikum.masterproject.integrationservice.client.customerservice;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@ConfigurationProperties(value = "services.customerservice")
@ConstructorBinding
@AllArgsConstructor
@Getter
public class CustomerServiceProperties {

  private final String url;
  private final int port;
  private final String apiKeyHeader;
  private final String apiKey;
}
