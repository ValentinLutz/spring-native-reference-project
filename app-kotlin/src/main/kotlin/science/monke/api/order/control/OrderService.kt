package science.monke.api.order.control

import org.springframework.stereotype.Service
import science.monke.api.order.entity.OrderRequest
import science.monke.api.order.entity.OrderResponse
import science.monke.internal.order.control.OrderMapper
import science.monke.internal.order.repository.OrderRepository
import science.monke.internal.workflow.Workflow
import science.monke.internal.workflow.WorkflowObject
import java.util.*
import java.util.stream.Collectors
import java.util.stream.StreamSupport

@Service
class OrderService(
    val orderRepository: OrderRepository,
    val orderMapper: OrderMapper,
    val defaultWorkflow: Workflow
) {

    fun getOrders(): Set<OrderResponse> {
        return StreamSupport.stream(orderRepository.findAll().spliterator(), false)
            .map(orderMapper::orderEntityToOrderResponse)
            .collect(Collectors.toSet())
    }

    fun postOrders(orderRequest: OrderRequest): OrderResponse {
        val orderEntity = orderMapper.orderRequestToOrderEntity(orderRequest)
        val workflowObject = WorkflowObject(orderEntity)
        defaultWorkflow.execute(workflowObject)
        return orderMapper.orderEntityToOrderResponse(orderEntity)
    }

    fun getOrder(orderId: String): Optional<OrderResponse> {
        return orderRepository.findByOrderId(orderId).map(orderMapper::orderEntityToOrderResponse)
    }
}
