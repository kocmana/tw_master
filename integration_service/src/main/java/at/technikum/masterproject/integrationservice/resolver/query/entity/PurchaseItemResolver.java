package at.technikum.masterproject.integrationservice.resolver.query.entity;

import at.technikum.masterproject.integrationservice.client.productservice.ProductInformationClient;
import at.technikum.masterproject.integrationservice.model.ecommerce.PurchaseItem;
import at.technikum.masterproject.integrationservice.model.product.Product;
import graphql.kickstart.tools.GraphQLResolver;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class PurchaseItemResolver implements GraphQLResolver<PurchaseItem> {

  private final ProductInformationClient productInformationClient;

  public PurchaseItemResolver(ProductInformationClient productInformationClient) {
    this.productInformationClient = productInformationClient;
  }

  public Product getProduct(PurchaseItem purchaseItem) {
    return productInformationClient.getProductById(purchaseItem.getProductId());
  }
}
