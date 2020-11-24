package at.technikum.masterproject.integrationservice.resolver.dataloader.query;

import static at.technikum.masterproject.integrationservice.resolver.dataloader.configuration.DataloaderConstants.PURCHASE_DATALOADER;

import at.technikum.masterproject.integrationservice.model.ecommerce.Purchase;
import graphql.kickstart.tools.GraphQLQueryResolver;
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
public class DataloaderEcommerceQueryResolver implements GraphQLQueryResolver {

  public CompletableFuture<Purchase> purchase(Long purchaseId, DataFetchingEnvironment environment) {
    log.info("Retrieved purchase query for purchaseId {}, resolving using dataloader", purchaseId);
    DataLoader<Long, Purchase> dataLoader = environment.getDataLoader(PURCHASE_DATALOADER);
    return dataLoader.load(purchaseId);
  }

}
