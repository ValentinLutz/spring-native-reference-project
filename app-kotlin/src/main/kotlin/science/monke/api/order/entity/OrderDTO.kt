package science.monke.api.order.entity

import java.util.*

data class OrderDTO(
    val status: OrderStatusDTO,
    val orderId: UUID,
    val items: List<OrderItemDTO> = emptyList()
) {}
