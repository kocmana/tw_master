package at.technikum.masterproject.commons.failure.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"at.technikum.masterproject.commons.failure"})
@Slf4j
public class FailureConfig {

  public FailureConfig() {
    log.warn("Failure annotations active.");
  }
}
