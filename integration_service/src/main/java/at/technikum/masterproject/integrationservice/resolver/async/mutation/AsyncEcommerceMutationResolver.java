package at.technikum.masterproject.integrationservice.resolver.async.mutation;

import at.technikum.masterproject.integrationservice.client.ecommerceservice.PriceClient;
import at.technikum.masterproject.integrationservice.client.ecommerceservice.PurchaseClient;
import at.technikum.masterproject.integrationservice.model.ecommerce.Price;
import at.technikum.masterproject.integrationservice.model.ecommerce.dto.CreatePriceInput;
import at.technikum.masterproject.integrationservice.model.ecommerce.dto.CreatePurchaseInput;
import at.technikum.masterproject.integrationservice.resolver.async.ResolverExecutor;
import graphql.kickstart.tools.GraphQLMutationResolver;
import java.util.concurrent.CompletableFuture;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(prefix = "services", name = "resolver-mode", havingValue = "ASYNC")
@RequiredArgsConstructor
public class AsyncEcommerceMutationResolver implements GraphQLMutationResolver {

  private final PriceClient priceClient;
  private final PurchaseClient purchaseClient;
  private final ResolverExecutor resolverExecutor;

  public CompletableFuture<Price> createPrice(CreatePriceInput price) {
    return priceClient.savePrice(price).toFuture();
  }

  public CompletableFuture<Long> createPurchase(CreatePurchaseInput purchase) {
    return purchaseClient.savePurchase(purchase).toFuture();
  }

}
