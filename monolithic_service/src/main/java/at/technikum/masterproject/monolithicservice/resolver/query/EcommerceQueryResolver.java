package at.technikum.masterproject.monolithicservice.resolver.query;

import at.technikum.masterproject.ecommerceservice.purchase.PurchaseService;
import at.technikum.masterproject.ecommerceservice.purchase.model.Purchase;
import at.technikum.masterproject.ecommerceservice.purchase.model.dto.PurchaseResponse;
import at.technikum.masterproject.ecommerceservice.purchase.model.mapper.PurchaseMapper;
import graphql.kickstart.tools.GraphQLQueryResolver;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class EcommerceQueryResolver implements GraphQLQueryResolver {

  private final PurchaseMapper purchaseMapper;
  private final PurchaseService purchaseService;

  public PurchaseResponse purchase(Integer purchaseId) {
    log.info("Retrieved purchase query for purchaseId {}", purchaseId);
    Purchase purchase = purchaseService.getPurchaseById(purchaseId);
    return purchaseMapper.purchaseToPurchaseResponse(purchase);
  }

}
