package at.technikum.masterproject.ecommerceservice.purchase.model.mapper;

import at.technikum.masterproject.ecommerceservice.purchase.model.Purchase;
import at.technikum.masterproject.ecommerceservice.purchase.model.PurchaseItem;
import at.technikum.masterproject.ecommerceservice.purchase.model.dto.PurchaseCreationRequest;
import at.technikum.masterproject.ecommerceservice.purchase.model.dto.PurchaseItemCreationRequest;
import at.technikum.masterproject.ecommerceservice.purchase.model.dto.PurchaseItemResponse;
import at.technikum.masterproject.ecommerceservice.purchase.model.dto.PurchaseResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PurchaseMapper {

  PurchaseResponse purchaseToPurchaseResponse(Purchase purchase);

  Purchase purchaseCreationRequestDtoToPurchase(PurchaseCreationRequest purchase);

  @Mapping(source = "purchase.id", target = "purchaseId")
  PurchaseItemResponse purchaseItemToPurchaseItemDto(PurchaseItem purchaseItem);

  PurchaseItem purchaseItemCreationRequestToPurchaseItem(PurchaseItemCreationRequest purchaseItem);

}
