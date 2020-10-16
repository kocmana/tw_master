package at.technikum.masterproject.integrationservice.model.ecommerce.dto;

import at.technikum.masterproject.integrationservice.model.ecommerce.PaymentType;
import at.technikum.masterproject.integrationservice.model.ecommerce.PurchaseItem;
import java.util.List;
import lombok.Value;

@Value
public class CreatePurchaseInput {

  Integer customerId;
  List<PurchaseItem> items;
  PaymentType paymentType;
}
