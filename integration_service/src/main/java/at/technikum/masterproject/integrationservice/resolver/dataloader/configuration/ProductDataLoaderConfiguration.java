package at.technikum.masterproject.integrationservice.resolver.dataloader.configuration;

import static at.technikum.masterproject.integrationservice.resolver.dataloader.configuration.DataloaderConstants.PRODUCT_INFORMATION_DATALOADER;
import static at.technikum.masterproject.integrationservice.resolver.dataloader.configuration.DataloaderConstants.PRODUCT_REVIEW_BY_CUSTOMER_DATALOADER;
import static at.technikum.masterproject.integrationservice.resolver.dataloader.configuration.DataloaderConstants.PRODUCT_REVIEW_BY_PRODUCT_DATALOADER;

import at.technikum.masterproject.integrationservice.client.productservice.ProductInformationClient;
import at.technikum.masterproject.integrationservice.client.productservice.ProductReviewClient;
import at.technikum.masterproject.integrationservice.model.product.Product;
import at.technikum.masterproject.integrationservice.model.product.ProductReview;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.dataloader.DataLoader;
import org.dataloader.DataLoaderOptions;
import org.dataloader.DataLoaderRegistry;
import org.dataloader.MappedBatchLoader;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnProperty(prefix = "services", name = "resolver-mode", havingValue = "DATALOADER")
@RequiredArgsConstructor
public class ProductDataLoaderConfiguration {

  private final DataLoaderExecutor dataLoaderExecutor;
  private final DataLoaderOptions dataLoaderOptions;

  private final ProductInformationClient productInformationClient;
  private final ProductReviewClient productReviewClient;

  @Bean("productServiceDataLoaderRegistry")
  public DataLoaderRegistry create() {
    DataLoaderRegistry registry = new DataLoaderRegistry();
    registry.register(PRODUCT_INFORMATION_DATALOADER, createProductInformationDataLoader());
    registry.register(PRODUCT_REVIEW_BY_PRODUCT_DATALOADER, createProductReviewByProductDataLoader());
    registry.register(PRODUCT_REVIEW_BY_CUSTOMER_DATALOADER, createProductReviewByCustomerDataLoader());

    return registry;
  }

  private DataLoader<Integer, List<ProductReview>> createProductReviewByProductDataLoader() {
    MappedBatchLoader<Integer, List<ProductReview>> batchLoadFunction = dataLoaderExecutor.generateBatchLoadFunction(
        productReviewClient::getAllProductReviewsForProduct);
    return DataLoader.newMappedDataLoader(batchLoadFunction, dataLoaderOptions);
  }

  private DataLoader<Integer, List<ProductReview>> createProductReviewByCustomerDataLoader() {
    MappedBatchLoader<Integer, List<ProductReview>> batchLoadFunction = dataLoaderExecutor.generateBatchLoadFunction(
        productReviewClient::getAllProductReviewsByCustomer);
    return DataLoader.newMappedDataLoader(batchLoadFunction);
  }

  private DataLoader<Integer, Product> createProductInformationDataLoader() {
    MappedBatchLoader<Integer, Product> batchLoadFunction = dataLoaderExecutor.generateBatchLoadFunction(
        productInformationClient::getProductById, Product::getId);
    return DataLoader.newMappedDataLoader(batchLoadFunction, dataLoaderOptions);
  }

}
