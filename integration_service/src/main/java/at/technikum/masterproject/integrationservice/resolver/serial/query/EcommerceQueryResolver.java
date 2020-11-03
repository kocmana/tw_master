package at.technikum.masterproject.integrationservice.resolver.serial.query;

import at.technikum.masterproject.integrationservice.client.ecommerceservice.PurchaseClient;
import at.technikum.masterproject.integrationservice.model.ecommerce.Purchase;
import graphql.kickstart.tools.GraphQLQueryResolver;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(prefix = "services", name = "resolver-mode", havingValue = "SERIAL", matchIfMissing = true)
@Slf4j
@RequiredArgsConstructor
public class EcommerceQueryResolver implements GraphQLQueryResolver {

  private final PurchaseClient purchaseClient;

  public Purchase purchase(Integer purchaseId) {
    log.info("Retrieved purchase query for purchaseId {}", purchaseId);
    return purchaseClient.getPurchase(purchaseId);
  }

}
