package science.monke.internal.order.boundary

import org.springframework.stereotype.Service
import science.monke.api.order.entity.OrderRequest
import science.monke.api.order.entity.OrderResponse
import science.monke.internal.order.control.OrderMapper
import science.monke.internal.order.repository.OrderRepository
import science.monke.internal.workflow.Workflow
import science.monke.internal.workflow.WorkflowObject
import science.monke.util.Error
import science.monke.util.exception.NotFoundException
import javax.transaction.Transactional

@Service
class OrderService(
    val orderRepository: OrderRepository,
    val orderMapper: OrderMapper,
    val defaultWorkflow: Workflow
) {

    @Transactional
    fun getOrders(): Set<OrderResponse> {
        return orderRepository.findAll()
            .map(orderMapper::orderEntityToOrderResponse)
            .toSet()
    }

    fun postOrders(orderRequest: OrderRequest): OrderResponse {
        val orderEntity = orderMapper.orderRequestToOrderEntity(orderRequest)
        val workflowObject = WorkflowObject(orderEntity)
        defaultWorkflow.execute(workflowObject)
        return orderMapper.orderEntityToOrderResponse(orderEntity)
    }

    @Transactional
    fun getOrder(orderId: String): OrderResponse {
        return orderRepository.findByOrderId(orderId)
            ?.let(orderMapper::orderEntityToOrderResponse)
            ?: throw NotFoundException(Error.ORDER_NOT_FOUND)
    }
}
