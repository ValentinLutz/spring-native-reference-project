package science.monke.internal.order.control;

import org.springframework.stereotype.Component;
import science.monke.api.order.entity.OrderRequest;
import science.monke.api.order.entity.OrderResponse;
import science.monke.api.order.entity.OrderStatus;
import science.monke.internal.order.entity.OrderEntity;

import java.time.OffsetDateTime;
import java.util.UUID;

@Component
public class OrderMapper {

  public OrderEntity orderRequestToOrderEntity(final OrderRequest orderRequest) {
    return OrderEntity.builder()
        .creationDate(OffsetDateTime.now())
        .orderStatus(OrderStatus.ORDER_PLACED)
        .orderId(UUID.randomUUID())
        .build();
  }

  public OrderResponse orderEntityToOrderResponse(final OrderEntity orderEntity) {
    return OrderResponse.builder()
        .creationDate(orderEntity.getCreationDate())
        .orderId(orderEntity.getOrderId())
        .status(orderEntity.getOrderStatus())
        .build();
  }
}
