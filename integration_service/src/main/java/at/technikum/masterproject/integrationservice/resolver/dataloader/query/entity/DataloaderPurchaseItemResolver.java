package at.technikum.masterproject.integrationservice.resolver.dataloader.query.entity;

import static at.technikum.masterproject.integrationservice.resolver.dataloader.configuration.DataloaderConstants.PRODUCT_INFORMATION_DATALOADER;

import at.technikum.masterproject.integrationservice.model.ecommerce.PurchaseItem;
import at.technikum.masterproject.integrationservice.model.product.Product;
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
public class DataloaderPurchaseItemResolver implements GraphQLResolver<PurchaseItem> {

  public CompletableFuture<Product> getProduct(PurchaseItem purchaseItem, DataFetchingEnvironment environment) {
    log.info("Retrieving product for purchaseItem {}, resolving using dataloader", purchaseItem.getId());
    DataLoader<Integer, Product> dataloader = environment.getDataLoader(PRODUCT_INFORMATION_DATALOADER);
    return dataloader.load(purchaseItem.getPurchaseId());
  }
}
