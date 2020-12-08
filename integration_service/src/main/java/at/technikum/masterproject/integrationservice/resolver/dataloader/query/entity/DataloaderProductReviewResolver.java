package at.technikum.masterproject.integrationservice.resolver.dataloader.query.entity;

import static at.technikum.masterproject.integrationservice.resolver.dataloader.configuration.DataloaderConstants.CUSTOMER_INFORMATION_DATALOADER;
import static at.technikum.masterproject.integrationservice.resolver.dataloader.configuration.DataloaderConstants.PRODUCT_INFORMATION_DATALOADER;

import at.technikum.masterproject.integrationservice.model.customer.Customer;
import at.technikum.masterproject.integrationservice.model.product.Product;
import at.technikum.masterproject.integrationservice.model.product.ProductReview;
import graphql.kickstart.tools.GraphQLResolver;
import graphql.schema.DataFetchingEnvironment;
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
public class DataloaderProductReviewResolver implements GraphQLResolver<ProductReview> {

  public CompletableFuture<Product> getProduct(ProductReview productReview, DataFetchingEnvironment environment) {
    log.debug("Retrieving product for review {}, resolving using dataloader", productReview.getId());
    DataLoader<Integer, Product> dataloader = environment.getDataLoader(PRODUCT_INFORMATION_DATALOADER);
    return dataloader.load(productReview.getProductId());

  }

  public CompletableFuture<Customer> getCustomer(ProductReview productReview, DataFetchingEnvironment environment) {
    log.debug("Retrieving customer for review {}, resolving using dataloader", productReview.getId());
    DataLoader<Integer, Customer> dataloader = environment.getDataLoader(CUSTOMER_INFORMATION_DATALOADER);
    return dataloader.load(productReview.getCustomerId());
  }

}
