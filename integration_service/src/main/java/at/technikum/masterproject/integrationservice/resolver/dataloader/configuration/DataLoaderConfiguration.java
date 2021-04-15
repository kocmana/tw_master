package at.technikum.masterproject.integrationservice.resolver.dataloader.configuration;

import org.dataloader.DataLoaderOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataLoaderConfiguration {

  @Bean
  DataLoaderOptions dataLoaderOptions(){
    return DataLoaderOptions.newOptions().setCachingEnabled(false);
  }

}
