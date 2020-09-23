package at.technikum.masterproject.commons.delay.config;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@ConfigurationProperties(prefix = "service.delay")
@ConstructorBinding
@AllArgsConstructor
@Builder
@Getter
public class DelayProperties {

  private boolean enableEndpointDelays = false;
  private boolean enableServiceDelays = false;
  private int serviceDelayMean = 0;
  private int serviceDelayStandardDeviation = 0;
  private boolean logDelays = false;
}
