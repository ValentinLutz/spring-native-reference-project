package science.monke.internal.order.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import science.monke.api.order.entity.OrderItemResponse;
import science.monke.api.order.entity.OrderRequest;
import science.monke.api.order.entity.OrderResponse;
import science.monke.api.order.entity.OrderStatus;
import science.monke.internal.order.entity.OrderEntity;
import science.monke.internal.order.entity.OrderId;
import science.monke.internal.order.entity.OrderItemEntity;
import science.monke.spring.config.CustomProperties;

import java.time.OffsetDateTime;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class OrderMapper {

  private final CustomProperties customProperties;
  private final OrderItemMapper orderItemMapper;

  @Autowired
  public OrderMapper(
      final CustomProperties customProperties, final OrderItemMapper orderItemMapper) {
    this.customProperties = customProperties;
    this.orderItemMapper = orderItemMapper;
  }

  public OrderEntity orderRequestToOrderEntity(final OrderRequest orderRequest) {
    final OrderEntity orderEntity =
        OrderEntity.builder()
            .creationDate(OffsetDateTime.now())
            .orderStatus(OrderStatus.ORDER_PLACED.name())
            .orderId(
                OrderId.randomOrderId(
                    customProperties.getRegion(), customProperties.getEnvironment()))
            .build();

    final Set<OrderItemEntity> orderItemEntities =
        orderRequest.items().stream()
            .map(
                itemName ->
                    orderItemMapper.orderItemRequestToOrderItemEntity(itemName, orderEntity))
            .collect(Collectors.toSet());

    orderEntity.setOrderItems(orderItemEntities);
    return orderEntity;
  }

  public OrderResponse orderEntityToOrderResponse(final OrderEntity orderEntity) {
    final Set<OrderItemResponse> orderItemResponses =
        orderEntity.getOrderItems().stream()
            .map(orderItemMapper::orderItemEntityToOrderItemResponse)
            .collect(Collectors.toSet());

    return OrderResponse.builder()
        .creationDate(orderEntity.getCreationDate())
        .orderId(orderEntity.getOrderId())
        .items(orderItemResponses)
        .status(OrderStatus.valueOf(orderEntity.getOrderStatus()))
        .build();
  }
}
