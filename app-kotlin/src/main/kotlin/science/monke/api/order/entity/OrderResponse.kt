package science.monke.api.order.entity

import science.monke.internal.order.entity.OrderId
import java.time.OffsetDateTime

data class OrderResponse(
    val status: OrderStatus,
    val orderId: OrderId,
    val items: Set<OrderItemResponse>,
    val creationDate: OffsetDateTime
)
