package science.monke.internal.order.repository

import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import science.monke.internal.order.entity.OrderEntity

interface OrderRepository : CoroutineCrudRepository<OrderEntity, Int> {

    suspend fun findByOrderId(orderId: String): OrderEntity?

}
