package at.technikum.masterproject.ecommerceservice.purchase.model.dto;

import at.technikum.masterproject.ecommerceservice.purchase.model.PaymentType;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class PurchaseResponse {

  private Integer id;
  private Integer customerId;
  private List<PurchaseItemResponse> items;
  private PaymentType paymentType;
}
