package science.monke.internal.event

import kotlinx.coroutines.flow.collect
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import science.monke.internal.order.entity.OrderEntity
import science.monke.internal.order.repository.OrderItemRepository
import science.monke.internal.order.repository.OrderRepository
import science.monke.internal.workflow.WorkflowObject

@Component
class OrderEvent(
    val orderRepository: OrderRepository,
    val orderItemRepository: OrderItemRepository
) : Event {

    override suspend fun getName(): EventName {
        return EventName.ORDER_EVENT
    }

    @Transactional
    override suspend fun execute(workflowObject: WorkflowObject): WorkflowObject {
        val order: OrderEntity = workflowObject.order

        orderRepository.save(order)
        orderItemRepository.saveAll(order.orderItems).collect()

        return workflowObject
    }
}
