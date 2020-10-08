package at.technikum.masterproject.integrationservice.config;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@ConfigurationProperties(prefix = "security")
@ConstructorBinding
@AllArgsConstructor
@Getter
public class SecurityWhitelistProperties {

  private final List<String> whitelist;
}
