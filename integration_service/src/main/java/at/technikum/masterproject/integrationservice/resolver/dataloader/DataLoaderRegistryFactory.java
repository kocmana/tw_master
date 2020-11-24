package at.technikum.masterproject.integrationservice.resolver.dataloader;

import static at.technikum.masterproject.integrationservice.resolver.dataloader.configuration.DataloaderConstants.CUSTOMER_INFORMATION_DATALOADER;
import static at.technikum.masterproject.integrationservice.resolver.dataloader.configuration.DataloaderConstants.CUSTOMER_NETWORK_DATALOADER;
import static at.technikum.masterproject.integrationservice.resolver.dataloader.configuration.DataloaderConstants.PRODUCT_INFORMATION_DATALOADER;
import static at.technikum.masterproject.integrationservice.resolver.dataloader.configuration.DataloaderConstants.PRODUCT_REVIEW_DATALOADER;
import static at.technikum.masterproject.integrationservice.resolver.dataloader.configuration.DataloaderConstants.PURCHASE_DATALOADER;
import static java.util.stream.Collectors.toConcurrentMap;

import at.technikum.masterproject.integrationservice.client.customerservice.CustomerInformationClient;
import at.technikum.masterproject.integrationservice.client.customerservice.CustomerNetworkClient;
import at.technikum.masterproject.integrationservice.client.ecommerceservice.PurchaseClient;
import at.technikum.masterproject.integrationservice.client.productservice.ProductInformationClient;
import at.technikum.masterproject.integrationservice.client.productservice.ProductReviewClient;
import at.technikum.masterproject.integrationservice.model.customer.Customer;
import at.technikum.masterproject.integrationservice.model.customer.CustomerNetwork;
import at.technikum.masterproject.integrationservice.model.ecommerce.Purchase;
import at.technikum.masterproject.integrationservice.model.product.Product;
import at.technikum.masterproject.integrationservice.model.product.ProductReview;
import java.util.AbstractMap.SimpleImmutableEntry;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Function;
import lombok.RequiredArgsConstructor;
import org.dataloader.DataLoader;
import org.dataloader.DataLoaderRegistry;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(prefix = "services", name = "resolver-mode", havingValue = "DATALOADER")
@RequiredArgsConstructor
public class DataLoaderRegistryFactory {

  private static final ExecutorService executorService = Executors.newCachedThreadPool();

  private final CustomerInformationClient customerInformationClient;
  private final CustomerNetworkClient customerNetworkClient;
  private final ProductInformationClient productInformationClient;
  private final ProductReviewClient productReviewClient;
  private final PurchaseClient purchaseClient;

  public DataLoaderRegistry create() {
    DataLoaderRegistry registry = new DataLoaderRegistry();
    registry.register(CUSTOMER_INFORMATION_DATALOADER, createCustomerInformationDataLoader());
    registry.register(CUSTOMER_NETWORK_DATALOADER, createCustomerNetworkDataLoader());
    registry.register(PRODUCT_INFORMATION_DATALOADER, createProductInformationDataLoader());
    registry.register(PRODUCT_REVIEW_DATALOADER, createProductReviewDataLoader());
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

  private DataLoader<Integer, Customer> createCustomerInformationDataLoader() {
    return DataLoader.newMappedDataLoader((Set<Integer> customerIds) ->
        CompletableFuture.supplyAsync(() -> customerIds.parallelStream()
                .map(customerInformationClient::getCustomerById)
                .collect(toConcurrentMap(Customer::getCustomerId, Function.identity()))
            , executorService));
  }

  private DataLoader<Integer, List<CustomerNetwork>> createCustomerNetworkDataLoader() {
    return DataLoader.newMappedDataLoader((Set<Integer> customerIds) ->
        CompletableFuture.supplyAsync(() -> customerIds.parallelStream()
                .map(customerId ->
                    new SimpleImmutableEntry<>(customerId, customerNetworkClient.getNetworkByCustomerId(customerId)))
                .collect(toConcurrentMap(SimpleImmutableEntry::getKey, SimpleImmutableEntry::getValue))
            , executorService));
  }
}
