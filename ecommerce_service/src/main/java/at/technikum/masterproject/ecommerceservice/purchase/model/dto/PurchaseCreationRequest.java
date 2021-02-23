package at.technikum.masterproject.ecommerceservice.purchase.model.dto;

import at.technikum.masterproject.ecommerceservice.purchase.model.domain.PaymentType;
import java.util.List;
import lombok.Value;

@Value
public class PurchaseCreationRequest {

  Integer customerId;
  List<PurchaseItemCreationRequest> items;
  PaymentType paymentType;
}
