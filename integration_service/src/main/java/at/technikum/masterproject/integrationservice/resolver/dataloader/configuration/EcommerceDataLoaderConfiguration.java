package at.technikum.masterproject.integrationservice.resolver.dataloader.configuration;

import at.technikum.masterproject.integrationservice.client.ecommerceservice.PurchaseClient;
import at.technikum.masterproject.integrationservice.model.ecommerce.Purchase;
import lombok.RequiredArgsConstructor;
import org.dataloader.DataLoader;
import org.dataloader.DataLoaderRegistry;
import org.dataloader.MappedBatchLoader;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

import static at.technikum.masterproject.integrationservice.resolver.dataloader.configuration.DataloaderConstants.PURCHASE_BY_CUSTOMER_DATALOADER;
import static at.technikum.masterproject.integrationservice.resolver.dataloader.configuration.DataloaderConstants.PURCHASE_BY_ID_DATALOADER;

@Configuration
@ConditionalOnProperty(prefix = "services", name = "resolver-mode", havingValue = "DATALOADER")
@RequiredArgsConstructor
public class EcommerceDataLoaderConfiguration {

  private final DataLoaderExecutor dataLoaderExecutor;

  private final PurchaseClient purchaseClient;

  @Bean("eCommerceServiceDataLoaderRegistry")
  public DataLoaderRegistry create() {
    DataLoaderRegistry registry = new DataLoaderRegistry();
    registry.register(PURCHASE_BY_ID_DATALOADER, createPurchaseByIdDataLoader());
    registry.register(PURCHASE_BY_CUSTOMER_DATALOADER, createPurchaseByCustomerDataLoader());

    return registry;
  }

  private DataLoader<Long, Purchase> createPurchaseByIdDataLoader() {
    MappedBatchLoader<Long, Purchase> batchLoadFunction = dataLoaderExecutor.generateBatchLoadFunction(
            purchaseClient::getPurchase, Purchase::getId);
    return DataLoader.newMappedDataLoader(batchLoadFunction);
  }

  private DataLoader<Integer, List<Purchase>> createPurchaseByCustomerDataLoader() {
    MappedBatchLoader<Integer, List<Purchase>> batchLoadFunction =
            dataLoaderExecutor.generateBatchLoadFunction(purchaseClient::getPurchasesForCustomer);
    return DataLoader.newMappedDataLoader(batchLoadFunction);
  }

}
