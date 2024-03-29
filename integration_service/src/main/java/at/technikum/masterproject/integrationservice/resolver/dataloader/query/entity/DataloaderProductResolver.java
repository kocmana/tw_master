package at.technikum.masterproject.integrationservice.resolver.dataloader.query.entity;

import static at.technikum.masterproject.integrationservice.resolver.dataloader.configuration.DataloaderConstants.ALL_PRICES_FOR_PRODUCT_DATALOADER;
import static at.technikum.masterproject.integrationservice.resolver.dataloader.configuration.DataloaderConstants.CURRENT_PRICE_FOR_PRODUCT_DATALOADER;
import static at.technikum.masterproject.integrationservice.resolver.dataloader.configuration.DataloaderConstants.PRODUCT_REVIEW_BY_PRODUCT_DATALOADER;

import at.technikum.masterproject.integrationservice.model.ecommerce.Price;
import at.technikum.masterproject.integrationservice.model.product.Product;
import at.technikum.masterproject.integrationservice.model.product.ProductReview;
import graphql.kickstart.tools.GraphQLResolver;
import graphql.schema.DataFetchingEnvironment;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dataloader.DataLoader;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(prefix = "services", name = "resolver-mode", havingValue = "DATALOADER")
@Slf4j
@RequiredArgsConstructor
public class DataloaderProductResolver implements GraphQLResolver<Product> {

  public CompletableFuture<List<ProductReview>> getReviews(Product product, DataFetchingEnvironment environment) {
    log.debug("Retrieving reviews for product {}, resolving using dataloader", product.getName());
    DataLoader<Integer, List<ProductReview>> dataloader =
        environment.getDataLoader(PRODUCT_REVIEW_BY_PRODUCT_DATALOADER);
    return dataloader.load(product.getId());
  }

  public CompletableFuture<Price> getPrice(Product product, DataFetchingEnvironment environment) {
    log.debug("Retrieving current price for product {}, resolving using dataloader", product.getName());
    DataLoader<Integer, Price> dataloader = environment.getDataLoader(CURRENT_PRICE_FOR_PRODUCT_DATALOADER);
    return dataloader.load(product.getId());
  }

  public CompletableFuture<List<Price>> getPrices(Product product, DataFetchingEnvironment environment) {
    log.debug("Retrieving all prices for product {}, resolving using dataloader", product.getName());
    DataLoader<Integer, List<Price>> dataloader = environment.getDataLoader(ALL_PRICES_FOR_PRODUCT_DATALOADER);
    return dataloader.load(product.getId());
  }

}
