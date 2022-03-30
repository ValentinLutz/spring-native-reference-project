package science.monke.internal.order.boundary

import org.springframework.stereotype.Service
import science.monke.api.order.entity.OrderRequest
import science.monke.api.order.entity.OrderResponse
import science.monke.internal.order.control.OrderMapper
import science.monke.internal.order.entity.OrderId
import science.monke.internal.order.repository.OrderRepository
import science.monke.spring.error.Error
import science.monke.spring.exception.NotFoundException
import science.monke.util.workflow.Context
import science.monke.util.workflow.DefaultWorkflow
import javax.transaction.Transactional

@Service
class OrderService(
    val orderRepository: OrderRepository,
    val orderMapper: OrderMapper,
    val defaultWorkflow: DefaultWorkflow
) {
    @Transactional
    fun getOrders(): Set<OrderResponse> {
        return orderRepository.findAll()
            .map(orderMapper::orderEntityToOrderResponse)
            .toSet()
    }
    
    fun postOrders(orderRequest: OrderRequest): OrderResponse {
        val orderEntity = orderMapper.orderRequestToOrderEntity(orderRequest)
        val context = Context(orderEntity)
        defaultWorkflow.execute(context)
        return orderMapper.orderEntityToOrderResponse(orderEntity)
    }

    @Transactional
    fun getOrder(orderId: OrderId): OrderResponse {
        return orderRepository.findByOrderId(orderId.toString())
            ?.let(orderMapper::orderEntityToOrderResponse)
            ?: throw NotFoundException(Error.ORDER_NOT_FOUND)
    }
}
