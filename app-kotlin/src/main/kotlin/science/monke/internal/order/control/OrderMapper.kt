package science.monke.internal.order.control

import org.springframework.stereotype.Component
import science.monke.api.order.entity.OrderItemResponse
import science.monke.api.order.entity.OrderRequest
import science.monke.api.order.entity.OrderResponse
import science.monke.internal.order.entity.OrderEntity
import science.monke.internal.order.entity.OrderItemEntity
import science.monke.internal.order.entity.OrderStatus
import java.time.OffsetDateTime
import java.util.stream.Collectors

@Component
class OrderMapper(
    val orderItemMapper: OrderItemMapper
) {
    fun orderRequestToOrderEntity(orderRequest: OrderRequest): OrderEntity {
        val orderItemEntities: Set<OrderItemEntity> =
            orderRequest.items.stream()
                .map { itemName -> orderItemMapper.orderItemRequestToOrderItemEntity(itemName) }
                .collect(Collectors.toSet())

        return OrderEntity(
            creationDate = OffsetDateTime.now(),
            orderStatus = OrderStatus.ORDER_PLACED,
            orderItems = orderItemEntities
        )
    }

    fun orderEntityToOrderResponse(orderEntity: OrderEntity): OrderResponse {
        val orderItemResponses: Set<OrderItemResponse> =
            orderEntity.orderItems.stream()
                .map(orderItemMapper::orderItemEntityToOrderItemResponse)
                .collect(Collectors.toSet())

        return OrderResponse(
            creationDate = orderEntity.creationDate,
            orderId = orderEntity.id.orEmpty(), // TODO
            items = orderItemResponses,
            status = orderEntity.orderStatus
        )
    }
}
