package at.technikum.masterproject.integrationservice.resolver.dataloader.configuration;

import static at.technikum.masterproject.integrationservice.resolver.dataloader.configuration.DataloaderConstants.PURCHASE_DATALOADER;
import static java.util.stream.Collectors.toConcurrentMap;

import at.technikum.masterproject.integrationservice.client.ecommerceservice.PurchaseClient;
import at.technikum.masterproject.integrationservice.model.ecommerce.Purchase;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.function.Function;
import lombok.RequiredArgsConstructor;
import org.dataloader.DataLoader;
import org.dataloader.DataLoaderRegistry;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnProperty(prefix = "services", name = "resolver-mode", havingValue = "DATALOADER")
@RequiredArgsConstructor
public class EcommerceDataLoaderConfiguration {

  private final ExecutorService executorService;

  private final PurchaseClient purchaseClient;

  @Bean("eCommerceServiceDataLoader")
  public DataLoaderRegistry create() {
    DataLoaderRegistry registry = new DataLoaderRegistry();
    registry.register(PURCHASE_DATALOADER, createPurchaseDataLoader());

    return registry;
  }

  private DataLoader<Long, Purchase> createPurchaseDataLoader() {
    return DataLoader.newMappedDataLoader((Set<Long> purchaseIds) ->
        CompletableFuture.supplyAsync(() -> purchaseIds.parallelStream()
                .map(purchaseClient::getPurchase)
                .collect(toConcurrentMap(Purchase::getId, Function.identity()))
            , executorService));
  }

}
