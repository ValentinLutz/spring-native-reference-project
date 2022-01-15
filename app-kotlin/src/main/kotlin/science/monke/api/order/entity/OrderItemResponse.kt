package science.monke.api.order.entity

import java.time.OffsetDateTime

data class OrderItemResponse(
    val name: String,
    val creationDate: OffsetDateTime,
)
