package at.technikum.masterproject.order.model.dto;

import at.technikum.masterproject.order.model.Order;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class OrderItemDto {

  private Order order;
  private Integer productId;
  private Integer amount;
  private Integer pricePerUnit;
}

