package science.monke.api.order.boundary

import io.swagger.v3.oas.annotations.media.ArraySchema
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.tags.Tag
import kotlinx.coroutines.flow.Flow
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import science.monke.api.order.entity.OrderRequest
import science.monke.api.order.entity.OrderResponse
import science.monke.internal.order.boundary.OrderService

@Tag(name = "order")
@RestController
@RequestMapping("/orders")
class OrderController(val orderService: OrderService) {

    @GetMapping
    @ApiResponse(
        responseCode = "200",
        content = [Content(array = ArraySchema(schema = Schema(implementation = OrderResponse::class)))]
    )
    fun getOrders(): Flow<OrderResponse> {
        return orderService.getOrders()
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiResponse(
        responseCode = "201",
        content = [Content(schema = Schema(implementation = OrderResponse::class))]
    )
    suspend fun postOrder(@RequestBody orderRequest: OrderRequest): OrderResponse {
        return orderService.postOrders(orderRequest)
    }

    @GetMapping("/{orderId}")
    @ApiResponse(
        responseCode = "200",
        content = [Content(schema = Schema(implementation = OrderResponse::class))]
    )
    suspend fun getOrder(@PathVariable orderId: String): OrderResponse {
        return orderService.getOrder(orderId)
    }
}
