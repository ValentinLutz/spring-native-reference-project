package science.monke.api.order.control;

import org.springframework.stereotype.Component;
import science.monke.api.order.entity.OrderResponse;
import science.monke.internal.order.entity.OrderEntity;

@Component
public class OrderResponseMapper {

  public OrderResponse orderEntityToOrderResponse(final OrderEntity orderEntity) {
    return OrderResponse.builder().orderId(orderEntity.getOrderId()).build();
  }
}
