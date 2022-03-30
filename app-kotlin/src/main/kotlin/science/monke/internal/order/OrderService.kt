package science.monke.internal.order

import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import science.monke.api.order.entity.OrderRequest
import science.monke.api.order.entity.OrderResponse
import science.monke.internal.order.control.OrderMapper
import science.monke.internal.order.repository.OrderRepository
import science.monke.spring.error.Error
import science.monke.spring.exception.NotFoundException
import science.monke.util.workflow.Context
import science.monke.util.workflow.DefaultWorkflow

@Service
class OrderService(
    private val orderRepository: OrderRepository,
    private val orderMapper: OrderMapper,
    private val defaultWorkflow: DefaultWorkflow
) {
    fun getOrders(): Set<OrderResponse> {
        return orderRepository.findAll()
            .map(orderMapper::orderEntityToOrderResponse)
            .toSet()
    }

    fun postOrders(orderRequest: OrderRequest): OrderResponse {
        val orderEntity = orderMapper.orderRequestToOrderEntity(orderRequest)
        orderRepository.save(orderEntity)
        val context = Context(orderEntity)
        defaultWorkflow.execute(context)
        return orderMapper.orderEntityToOrderResponse(orderEntity)
    }

    fun getOrder(orderId: String): OrderResponse {
        return orderRepository.findByIdOrNull(orderId)
            ?.let(orderMapper::orderEntityToOrderResponse)
            ?: throw NotFoundException(Error.ORDER_NOT_FOUND)
    }
}
