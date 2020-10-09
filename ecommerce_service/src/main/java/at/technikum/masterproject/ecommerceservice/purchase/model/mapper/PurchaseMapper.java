package at.technikum.masterproject.ecommerceservice.purchase.model.mapper;

import at.technikum.masterproject.ecommerceservice.purchase.model.Purchase;
import at.technikum.masterproject.ecommerceservice.purchase.model.PurchaseItem;
import at.technikum.masterproject.ecommerceservice.purchase.model.dto.PurchaseDto;
import at.technikum.masterproject.ecommerceservice.purchase.model.dto.PurchaseItemDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PurchaseMapper {

  PurchaseDto purchaseToPurchaseDto(Purchase purchase);

  Purchase purchaseDtoToPurchase(PurchaseDto purchase);

  @Mapping(source = "purchase.id", target = "purchaseId")
  PurchaseItemDto purchaseItemToPurchaseItemDto(PurchaseItem purchaseItem);

  PurchaseItem purchaseItemDtoToPurchaseItem(PurchaseItemDto purchase);

}
