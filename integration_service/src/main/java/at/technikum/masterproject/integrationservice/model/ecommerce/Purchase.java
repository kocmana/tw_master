package at.technikum.masterproject.integrationservice.model.ecommerce;

import java.util.List;
import lombok.Value;

@Value
public class Purchase {

  Long id;
  Integer customerId;
  List<PurchaseItem> items;
  PaymentType paymentType;
}
