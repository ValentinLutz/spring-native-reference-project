package science.monke.api.order.entity

import science.monke.internal.order.entity.OrderStatus
import java.time.OffsetDateTime

data class OrderResponse(
    val status: OrderStatus,
    val orderId: String,
    val items: Set<OrderItemResponse>,
    val creationDate: OffsetDateTime
)
