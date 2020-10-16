package at.technikum.masterproject.integrationservice.resolver.mutation;

import at.technikum.masterproject.integrationservice.client.ecommerceservice.PriceClient;
import at.technikum.masterproject.integrationservice.client.ecommerceservice.PurchaseClient;
import at.technikum.masterproject.integrationservice.model.ecommerce.Price;
import at.technikum.masterproject.integrationservice.model.ecommerce.dto.CreatePriceInput;
import at.technikum.masterproject.integrationservice.model.ecommerce.dto.CreatePurchaseInput;
import graphql.kickstart.tools.GraphQLMutationResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EcommerceMutationResolver implements GraphQLMutationResolver {

  private final PriceClient priceClient;
  private final PurchaseClient purchaseClient;

  @Autowired
  public EcommerceMutationResolver(PriceClient priceClient, PurchaseClient purchaseClient) {
    this.priceClient = priceClient;
    this.purchaseClient = purchaseClient;
  }

  public Price createPrice(CreatePriceInput price) {
    return priceClient.savePrice(price);
  }

  public long createPurchase(CreatePurchaseInput purchase) {
    return purchaseClient.savePurchase(purchase);
  }

}
