package at.technikum.masterproject.order.model.mapper;

import at.technikum.masterproject.order.model.Order;
import at.technikum.masterproject.order.model.OrderItem;
import at.technikum.masterproject.order.model.dto.OrderDto;
import at.technikum.masterproject.order.model.dto.OrderItemDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderMapper {

  OrderDto orderToOrderDto(Order order);

  Order orderDtoToOrder(OrderDto orderDto);

  OrderItemDto orderItemToOrderItemDto(OrderItem orderItem);

  OrderItem orderItemDtoToOrderItem(OrderItemDto orderItemDto);
}
