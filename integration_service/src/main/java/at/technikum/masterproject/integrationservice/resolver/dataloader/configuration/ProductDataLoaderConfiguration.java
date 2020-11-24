package at.technikum.masterproject.integrationservice.resolver.dataloader.configuration;

import static at.technikum.masterproject.integrationservice.resolver.dataloader.configuration.DataloaderConstants.PRODUCT_INFORMATION_DATALOADER;
import static at.technikum.masterproject.integrationservice.resolver.dataloader.configuration.DataloaderConstants.PRODUCT_REVIEW_DATALOADER;
import static java.util.stream.Collectors.toConcurrentMap;

import at.technikum.masterproject.integrationservice.client.productservice.ProductInformationClient;
import at.technikum.masterproject.integrationservice.client.productservice.ProductReviewClient;
import at.technikum.masterproject.integrationservice.model.product.Product;
import at.technikum.masterproject.integrationservice.model.product.ProductReview;
import java.util.AbstractMap.SimpleImmutableEntry;
import java.util.List;
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
public class ProductDataLoaderConfiguration {

  private final ExecutorService executorService;

  private final ProductInformationClient productInformationClient;
  private final ProductReviewClient productReviewClient;

  @Bean("productServiceDataLoader")
  public DataLoaderRegistry create() {
    DataLoaderRegistry registry = new DataLoaderRegistry();
    registry.register(PRODUCT_INFORMATION_DATALOADER, createProductInformationDataLoader());
    registry.register(PRODUCT_REVIEW_DATALOADER, createProductReviewDataLoader());

    return registry;
  }

  private DataLoader<Integer, List<ProductReview>> createProductReviewDataLoader() {
    return DataLoader.newMappedDataLoader((Set<Integer> productIds) ->
        CompletableFuture.supplyAsync(() -> productIds.parallelStream()
                .map(productId -> new SimpleImmutableEntry<>(productId,
                    productReviewClient.getAllProductReviewsForProduct(productId)))
                .collect(toConcurrentMap(SimpleImmutableEntry::getKey, SimpleImmutableEntry::getValue))
            , executorService));
  }

  private DataLoader<Integer, Product> createProductInformationDataLoader() {
    return DataLoader.newMappedDataLoader((Set<Integer> productIds) ->
        CompletableFuture.supplyAsync(() -> productIds.parallelStream()
                .map(productInformationClient::getProductById)
                .collect(toConcurrentMap(Product::getId, Function.identity()))
            , executorService));
  }

}
