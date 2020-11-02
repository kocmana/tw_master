package at.technikum.masterproject.integrationservice.client;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.springframework.boot.context.properties.ConstructorBinding;

@ConstructorBinding
@FieldDefaults(level = AccessLevel.PROTECTED)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public abstract class ServiceProperties {

  String url;
  int port;
  AuthenticationType authenticationType;
  String username;
  String password;
  String apiKey;
  String apiKeyHeader;
}
