package at.technikum.masterproject.integrationservice.resolver.dataloader.query.entity;

import static at.technikum.masterproject.integrationservice.resolver.dataloader.configuration.DataloaderConstants.CUSTOMER_NETWORK_DATALOADER;

import at.technikum.masterproject.integrationservice.client.ecommerceservice.PurchaseClient;
import at.technikum.masterproject.integrationservice.client.productservice.ProductReviewClient;
import at.technikum.masterproject.integrationservice.model.customer.Customer;
import at.technikum.masterproject.integrationservice.model.customer.CustomerNetwork;
import at.technikum.masterproject.integrationservice.model.ecommerce.Purchase;
import at.technikum.masterproject.integrationservice.model.product.ProductReview;
import at.technikum.masterproject.integrationservice.resolver.async.ResolverExecutor;
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
public class DataloaderCustomerResolver implements GraphQLResolver<Customer> {

  private final ProductReviewClient productReviewClient;
  private final PurchaseClient purchaseClient;
  private final ResolverExecutor resolverExecutor;

  public CompletableFuture<List<ProductReview>> getReviews(Customer customer) {
    log.info("Retrieving reviews for customer {}, resolving asynchronously", customer.getCustomerId());
    return resolverExecutor.resolve(() -> productReviewClient.getAllProductReviewsByCustomer(customer.getCustomerId()));
  }

  public CompletableFuture<List<CustomerNetwork>> getNetwork(Customer customer, DataFetchingEnvironment environment) {
    log.info("Retrieving network for customer {}, resolving with dataloader", customer.getCustomerId());
    DataLoader<Integer, List<CustomerNetwork>> dataloader = environment.getDataLoader(CUSTOMER_NETWORK_DATALOADER);
    return dataloader.load(customer.getCustomerId());
  }

  public CompletableFuture<List<Purchase>> getPurchases(Customer customer) {
    log.info("Retrieving purchases for customer {}, resolving asynchronously", customer.getCustomerId());
    return resolverExecutor.resolve(() -> purchaseClient.getPurchasesForCustomer(customer.getCustomerId()));
  }
}
