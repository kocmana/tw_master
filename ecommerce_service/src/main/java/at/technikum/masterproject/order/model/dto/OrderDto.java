package at.technikum.masterproject.order.model.dto;

import at.technikum.masterproject.order.model.PaymentType;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class OrderDto {

  private Long id;
  private Integer customerId;
  private List<OrderItemDto> items;
  private PaymentType paymentType;
}
