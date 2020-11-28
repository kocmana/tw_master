package at.technikum.masterproject.integrationservice.resolver.dataloader.query.entity;

import at.technikum.masterproject.integrationservice.model.customer.Customer;
import at.technikum.masterproject.integrationservice.model.customer.CustomerNetwork;
import at.technikum.masterproject.integrationservice.model.ecommerce.Purchase;
import at.technikum.masterproject.integrationservice.model.product.ProductReview;
import graphql.kickstart.tools.GraphQLResolver;
import graphql.schema.DataFetchingEnvironment;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dataloader.DataLoader;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import static at.technikum.masterproject.integrationservice.resolver.dataloader.configuration.DataloaderConstants.*;

@Component
@ConditionalOnProperty(prefix = "services", name = "resolver-mode", havingValue = "DATALOADER")
@Slf4j
@RequiredArgsConstructor
public class DataloaderCustomerResolver implements GraphQLResolver<Customer> {

  public CompletableFuture<List<ProductReview>> getReviews(Customer customer, DataFetchingEnvironment environment) {
    log.info("Retrieving product reviews for customer {}, resolving with dataloader", customer.getCustomerId());
    DataLoader<Integer, List<ProductReview>> dataloader = environment.getDataLoader(PRODUCT_REVIEW_BY_CUSTOMER_DATALOADER);
    return dataloader.load(customer.getCustomerId());
  }

  public CompletableFuture<List<CustomerNetwork>> getNetwork(Customer customer, DataFetchingEnvironment environment) {
    log.info("Retrieving network for customer {}, resolving with dataloader", customer.getCustomerId());
    DataLoader<Integer, List<CustomerNetwork>> dataloader = environment.getDataLoader(CUSTOMER_NETWORK_DATALOADER);
    return dataloader.load(customer.getCustomerId());
  }

  public CompletableFuture<List<Purchase>> getPurchases(Customer customer, DataFetchingEnvironment environment) {
    log.info("Retrieving purchases for customer {}, resolving with dataloader", customer.getCustomerId());
    DataLoader<Integer, List<Purchase>> dataloader = environment.getDataLoader(PURCHASE_BY_CUSTOMER_DATALOADER);
    return dataloader.load(customer.getCustomerId());
  }
}
