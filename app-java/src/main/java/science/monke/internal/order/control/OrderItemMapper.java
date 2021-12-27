package science.monke.internal.order.control;

import org.springframework.stereotype.Component;
import science.monke.api.order.entity.OrderItemResponse;
import science.monke.internal.order.entity.OrderEntity;
import science.monke.internal.order.entity.OrderItemEntity;

@Component
public class OrderItemMapper {

  public OrderItemResponse orderItemEntityToOrderItemResponse(
      final OrderItemEntity orderItemEntity) {
    return OrderItemResponse.builder().name(orderItemEntity.getItemName()).build();
  }

  public OrderItemEntity orderItemRequestToOrderItemEntity(
          final String name, final OrderEntity orderEntity) {
    return OrderItemEntity.builder()
        .itemName(name)
        .creationDate(orderEntity.getCreationDate())
        .orderId(orderEntity.getOrderId())
        .build();
  }
}
