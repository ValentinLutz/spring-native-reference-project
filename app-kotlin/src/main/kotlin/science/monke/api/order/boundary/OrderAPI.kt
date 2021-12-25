package science.monke.api.order.boundary

import io.swagger.v3.oas.annotations.media.ArraySchema
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Mono
import science.monke.api.order.control.OrderService
import science.monke.api.order.entity.NewOrderDTO
import science.monke.api.order.entity.OrderDTO
import science.monke.api.order.entity.OrderStatusDTO
import java.util.*

@Tag(name = "Orders")
@RestController
@RequestMapping("/orders")
class OrderAPI(val orderService: OrderService) {

    @GetMapping
    @ApiResponse(
        responseCode = "200",
        content = [Content(array = ArraySchema(schema = Schema(implementation = OrderDTO::class)))]
    )
    fun getOrders(): Mono<List<OrderDTO>> {
        return Mono.just(listOf(OrderDTO(orderId = UUID.randomUUID(), status = OrderStatusDTO.ORDER_PLACED)))
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiResponse(
        responseCode = "201",
        content = [Content(schema = Schema(implementation = OrderDTO::class))]
    )
    fun postOrder(@RequestParam newOrder: NewOrderDTO): Mono<OrderDTO> {
        return Mono.just(OrderDTO(orderId = UUID.randomUUID(), status = OrderStatusDTO.ORDER_IN_PROGRESS))
    }


    @GetMapping("/{orderId}")
    @ApiResponse(
        responseCode = "200",
        content = [Content(schema = Schema(implementation = OrderDTO::class))]
    )
    fun getOrder(@PathVariable(value = "orderId") orderId: Int): Mono<OrderDTO> {
        return Mono.just(OrderDTO(orderId = UUID.randomUUID(), status = OrderStatusDTO.ORDER_CANCELED))
    }
}
