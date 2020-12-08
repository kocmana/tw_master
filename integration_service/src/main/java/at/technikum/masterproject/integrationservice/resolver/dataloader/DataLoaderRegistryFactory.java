package at.technikum.masterproject.integrationservice.resolver.dataloader;

import lombok.extern.slf4j.Slf4j;
import org.dataloader.DataLoaderRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(prefix = "services", name = "resolver-mode", havingValue = "DATALOADER")
@Slf4j
public class DataLoaderRegistryFactory {

  private final DataLoaderRegistry registry;

  @Autowired
  public DataLoaderRegistryFactory(
      @Qualifier("customerServiceDataLoaderRegistry") DataLoaderRegistry customerDataLoaderRegistry,
      @Qualifier("productServiceDataLoaderRegistry") DataLoaderRegistry productDataLoaderRegistry,
      @Qualifier("eCommerceServiceDataLoaderRegistry") DataLoaderRegistry ecommerceDataLoaderRegistry
  ) {
    DataLoaderRegistry combinedDataloaderRegistry = customerDataLoaderRegistry.combine(productDataLoaderRegistry);
    combinedDataloaderRegistry = combinedDataloaderRegistry.combine(ecommerceDataLoaderRegistry);
    registry = combinedDataloaderRegistry;
  }

  public DataLoaderRegistry create() {
    return registry;
  }

}
