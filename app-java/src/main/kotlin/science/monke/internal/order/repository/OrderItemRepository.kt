package science.monke.internal.order.repository;

import org.springframework.data.repository.CrudRepository
import science.monke.internal.order.entity.OrderItemEntity
import java.util.*

interface OrderItemRepository : CrudRepository<OrderItemEntity, Long> {
    fun findByOrderId(orderId: UUID): Set<OrderItemEntity>
}
