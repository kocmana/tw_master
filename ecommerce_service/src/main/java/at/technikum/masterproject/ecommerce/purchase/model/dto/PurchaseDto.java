package at.technikum.masterproject.ecommerce.purchase.model.dto;

import at.technikum.masterproject.ecommerce.purchase.model.PaymentType;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class PurchaseDto {

  private Long id;
  private Integer customerId;
  private List<PurchaseItemDto> items;
  private PaymentType paymentType;
}
