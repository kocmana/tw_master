package at.technikum.masterproject.integrationservice.resolver.serial.mutation;

import at.technikum.masterproject.integrationservice.client.ecommerceservice.PriceClient;
import at.technikum.masterproject.integrationservice.client.ecommerceservice.PurchaseClient;
import at.technikum.masterproject.integrationservice.model.ecommerce.Price;
import at.technikum.masterproject.integrationservice.model.ecommerce.dto.CreatePriceInput;
import at.technikum.masterproject.integrationservice.model.ecommerce.dto.CreatePurchaseInput;
import graphql.kickstart.tools.GraphQLMutationResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(prefix = "services", name = "resolver-mode", havingValue = "SERIAL", matchIfMissing = true)
@RequiredArgsConstructor
public class EcommerceMutationResolver implements GraphQLMutationResolver {

  private final PriceClient priceClient;
  private final PurchaseClient purchaseClient;

  public Price createPrice(CreatePriceInput price) {
    return priceClient.savePrice(price).block();
  }

  public long createPurchase(CreatePurchaseInput purchase) {
    return purchaseClient.savePurchase(purchase).block();
  }

}
