package at.technikum.masterproject.monolithicservice.resolver.query.entity;

import at.technikum.masterproject.ecommerceservice.purchase.model.PurchaseItem;
import at.technikum.masterproject.productservice.productinformation.ProductInformationService;
import at.technikum.masterproject.productservice.productinformation.model.Product;
import at.technikum.masterproject.productservice.productinformation.model.dto.ProductResponse;
import at.technikum.masterproject.productservice.productinformation.model.mapper.ProductMapper;
import graphql.kickstart.tools.GraphQLResolver;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class PurchaseItemResolver implements GraphQLResolver<PurchaseItem> {

  private final ProductMapper productMapper;
  private final ProductInformationService productInformationService;

  public ProductResponse getProduct(PurchaseItem purchaseItem) {
    Product product = productInformationService.retrieveProductById(purchaseItem.getProductId());
    return productMapper.productToProductResponse(product);
  }
}
