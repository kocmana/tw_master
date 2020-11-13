package at.technikum.masterproject.integrationservice.resolver.serial.query.entity;

import at.technikum.masterproject.integrationservice.client.productservice.ProductInformationClient;
import at.technikum.masterproject.integrationservice.model.ecommerce.PurchaseItem;
import at.technikum.masterproject.integrationservice.model.product.Product;
import graphql.kickstart.tools.GraphQLResolver;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(prefix = "services", name = "resolver-mode", havingValue = "SERIAL", matchIfMissing = true)
@Slf4j
public class PurchaseItemResolver implements GraphQLResolver<PurchaseItem> {

  private final ProductInformationClient productInformationClient;

  public PurchaseItemResolver(ProductInformationClient productInformationClient) {
    this.productInformationClient = productInformationClient;
  }

  public Product getProduct(PurchaseItem purchaseItem) {
    return productInformationClient.getProductById(purchaseItem.getProductId())
        .block();
  }
}
