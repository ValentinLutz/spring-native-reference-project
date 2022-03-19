package science.monke.internal.order.repository

import kotlinx.coroutines.flow.Flow
import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import science.monke.internal.order.entity.OrderItemEntity

interface OrderItemRepository : CoroutineCrudRepository<OrderItemEntity, Int> {

    fun findByOrderId(orderId: String): Flow<OrderItemEntity>
}
