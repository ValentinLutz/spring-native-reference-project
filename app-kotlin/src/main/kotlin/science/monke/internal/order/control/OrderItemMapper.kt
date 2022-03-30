package science.monke.internal.order.control

import org.springframework.stereotype.Component
import science.monke.api.order.entity.OrderItemResponse
import science.monke.internal.order.entity.OrderItemEntity

@Component
class OrderItemMapper {
    fun orderItemEntityToOrderItemResponse(orderItemEntity: OrderItemEntity): OrderItemResponse {
        return OrderItemResponse(
            name = orderItemEntity.itemName,
            creationDate = orderItemEntity.creationDate
        )
    }

    fun orderItemRequestToOrderItemEntity(name: String): OrderItemEntity {
        return OrderItemEntity(
            itemName = name
        )
    }
}
