package science.monke.internal.order.repository

import org.springframework.data.repository.CrudRepository
import science.monke.internal.order.entity.OrderEntity
import science.monke.internal.order.entity.OrderId
import java.util.*

interface OrderRepository : CrudRepository<OrderEntity, Int> {
    fun findByOrderId(orderId: OrderId): Optional<OrderEntity>
}
