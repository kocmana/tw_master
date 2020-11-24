package at.technikum.masterproject.integrationservice.resolver.dataloader.configuration;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataLoaderExecutorConfiguration {

  private final ExecutorService executorService;

  public DataLoaderExecutorConfiguration() {
    executorService = Executors.newCachedThreadPool();
  }

  @Bean
  ExecutorService dataloaderExecutor(){
    return executorService;
  }

}
