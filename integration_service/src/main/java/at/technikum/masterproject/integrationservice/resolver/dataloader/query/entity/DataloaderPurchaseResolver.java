package at.technikum.masterproject.integrationservice.resolver.dataloader.query.entity;

import static at.technikum.masterproject.integrationservice.resolver.dataloader.configuration.DataloaderConstants.CUSTOMER_INFORMATION_DATALOADER;

import at.technikum.masterproject.integrationservice.model.customer.Customer;
import at.technikum.masterproject.integrationservice.model.ecommerce.Purchase;
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
public class DataloaderPurchaseResolver implements GraphQLResolver<Purchase> {

  public CompletableFuture<Customer> getCustomer(Purchase purchase, DataFetchingEnvironment environment) {
    log.debug("Retrieving customer for purchase {}, resolving with dataloader", purchase.getId());
    DataLoader<Integer, Customer> dataloader = environment.getDataLoader(CUSTOMER_INFORMATION_DATALOADER);
    return dataloader.load(purchase.getCustomerId());
  }
}
