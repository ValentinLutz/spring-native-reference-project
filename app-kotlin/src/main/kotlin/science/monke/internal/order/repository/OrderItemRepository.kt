package science.monke.internal.order.repository

import org.springframework.data.repository.CrudRepository
import science.monke.internal.order.entity.OrderItemEntity

interface OrderItemRepository : CrudRepository<OrderItemEntity, Int>
