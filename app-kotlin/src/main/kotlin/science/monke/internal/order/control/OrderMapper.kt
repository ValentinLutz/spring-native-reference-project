package science.monke.internal.order.control

import org.springframework.stereotype.Component
import science.monke.api.order.entity.OrderItemResponse
import science.monke.api.order.entity.OrderRequest
import science.monke.api.order.entity.OrderResponse
import science.monke.api.order.entity.OrderStatus
import science.monke.internal.order.entity.OrderEntity
import science.monke.internal.order.entity.OrderItemEntity
import science.monke.spring.config.CustomProperties
import java.time.OffsetDateTime
import java.util.stream.Collectors

@Component
class OrderMapper(
    val customProperties: CustomProperties,
    val orderItemMapper: OrderItemMapper,
    val orderIdGenerator: OrderIdGenerator
) {

    fun orderRequestToOrderEntity(orderRequest: OrderRequest): OrderEntity {
        val orderEntity = OrderEntity(
            creationDate = OffsetDateTime.now(),
            orderStatus = OrderStatus.ORDER_PLACED.name,
            orderId = orderIdGenerator.random(customProperties.region, customProperties.environment)
        )

        val orderItemEntities: Set<OrderItemEntity> =
            orderRequest.items.stream()
                .map { itemName -> orderItemMapper.orderItemRequestToOrderItemEntity(itemName, orderEntity) }
                .collect(Collectors.toSet())

        orderEntity.orderItems = orderItemEntities
        return orderEntity
    }

    fun orderEntityToOrderResponse(orderEntity: OrderEntity): OrderResponse {
        val orderItemResponses: Set<OrderItemResponse> =
            orderEntity.orderItems.stream()
                .map(orderItemMapper::orderItemEntityToOrderItemResponse)
                .collect(Collectors.toSet())

        return OrderResponse(
            creationDate = orderEntity.creationDate,
            orderId = orderEntity.orderId,
            items = orderItemResponses,
            status = OrderStatus.valueOf(orderEntity.orderStatus)
        )
    }
}
