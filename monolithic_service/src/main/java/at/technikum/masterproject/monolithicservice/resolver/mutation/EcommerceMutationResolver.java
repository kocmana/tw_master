package at.technikum.masterproject.monolithicservice.resolver.mutation;

import at.technikum.masterproject.ecommerceservice.price.PriceService;
import at.technikum.masterproject.ecommerceservice.price.model.Price;
import at.technikum.masterproject.ecommerceservice.price.model.dto.PriceDto;
import at.technikum.masterproject.ecommerceservice.price.model.mapper.PriceMapper;
import at.technikum.masterproject.ecommerceservice.purchase.PurchaseService;
import at.technikum.masterproject.ecommerceservice.purchase.model.Purchase;
import at.technikum.masterproject.ecommerceservice.purchase.model.dto.PurchaseCreationRequest;
import at.technikum.masterproject.ecommerceservice.purchase.model.mapper.PurchaseMapper;
import graphql.kickstart.tools.GraphQLMutationResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(prefix = "services", name = "resolver-mode", havingValue = "SERIAL", matchIfMissing = true)
@RequiredArgsConstructor
public class EcommerceMutationResolver implements GraphQLMutationResolver {

  private final PriceMapper priceMapper;
  private final PriceService priceService;
  private final PurchaseMapper purchaseMapper;
  private final PurchaseService purchaseService;

  public PriceDto createPrice(PriceDto priceDto) {
    Price price = priceMapper.priceDtoToPrice(priceDto);
    price = priceService.savePriceForProduct(price);
    return priceMapper.priceToPriceDto(price);
  }

  public long createPurchase(PurchaseCreationRequest purchaseDto) {
    Purchase purchase = purchaseMapper.purchaseCreationRequestDtoToPurchase(purchaseDto);
    return purchaseService.savePurchase(purchase);
  }

}
