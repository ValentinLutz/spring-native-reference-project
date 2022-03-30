package science.monke.internal.order.repository

import org.springframework.data.repository.CrudRepository
import science.monke.internal.order.entity.OrderEntity

interface OrderRepository : CrudRepository<OrderEntity, Int> {
    fun findByOrderId(orderId: String): OrderEntity?
}
