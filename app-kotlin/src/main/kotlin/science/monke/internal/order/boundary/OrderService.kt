package science.monke.internal.order.boundary

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEmpty
import kotlinx.coroutines.flow.toSet
import org.springframework.stereotype.Service
import science.monke.api.order.entity.OrderRequest
import science.monke.api.order.entity.OrderResponse
import science.monke.internal.order.control.OrderMapper
import science.monke.internal.order.entity.OrderEntity
import science.monke.internal.order.repository.OrderItemRepository
import science.monke.internal.order.repository.OrderRepository
import science.monke.internal.workflow.Workflow
import science.monke.internal.workflow.WorkflowObject
import science.monke.util.Error
import science.monke.util.exception.NotFoundException

@Service
class OrderService(
    val orderRepository: OrderRepository,
    val orderItemRepository: OrderItemRepository,
    val orderMapper: OrderMapper,
    val defaultWorkflow: Workflow
) {

    fun getOrders(): Flow<OrderResponse> {
        return orderRepository.findAll()
            .map(this::getOrderItems)
            .map(orderMapper::orderEntityToOrderResponse)
            .onEmpty { throw NotFoundException(Error.ORDER_NOT_FOUND) }
    }

    private suspend fun getOrderItems(orderEntity: OrderEntity): OrderEntity {
        orderEntity.orderItems = orderItemRepository.findByOrderId(orderEntity.orderId).toSet()
        return orderEntity;
    }

    suspend fun postOrders(orderRequest: OrderRequest): OrderResponse {
        val orderEntity = orderMapper.orderRequestToOrderEntity(orderRequest)
        val workflowObject = WorkflowObject(orderEntity)
        defaultWorkflow.execute(workflowObject)
        return orderMapper.orderEntityToOrderResponse(orderEntity)
    }

    suspend fun getOrder(orderId: String): OrderResponse {
        return orderRepository.findByOrderId(orderId)
            ?.let(orderMapper::orderEntityToOrderResponse)
            ?: throw NotFoundException(Error.ORDER_NOT_FOUND)
    }
}
