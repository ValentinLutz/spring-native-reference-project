package science.monke.api.order.boundary

import io.swagger.v3.oas.annotations.media.ArraySchema
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException
import science.monke.api.order.control.OrderService
import science.monke.api.order.entity.OrderRequest
import science.monke.api.order.entity.OrderResponse

@Tag(name = "order")
@RestController
@RequestMapping("/orders")
class OrderController(val orderService: OrderService) {

    @GetMapping
    @ApiResponse(
        responseCode = "200",
        content = [Content(array = ArraySchema(schema = Schema(implementation = OrderResponse::class)))]
    )
    fun getOrders(): Set<OrderResponse> {
        val orders: Set<OrderResponse> = orderService.getOrders()
        if (orders.isEmpty()) {
            throw ResponseStatusException(HttpStatus.NOT_FOUND)
        }
        return orders
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiResponse(
        responseCode = "201",
        content = [Content(schema = Schema(implementation = OrderResponse::class))]
    )
    fun postOrder(@RequestBody orderRequest: OrderRequest): OrderResponse {
        return orderService.postOrders(orderRequest)
    }

    @GetMapping("/{orderId}")
    @ApiResponse(
        responseCode = "200",
        content = [Content(schema = Schema(implementation = OrderResponse::class))]
    )
    fun getOrder(@PathVariable orderId: String): OrderResponse {
        return orderService
            .getOrder(orderId)
            .orElseThrow { ResponseStatusException(HttpStatus.NOT_FOUND) }
    }
}
