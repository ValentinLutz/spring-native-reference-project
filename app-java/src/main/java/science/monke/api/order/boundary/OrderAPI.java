package science.monke.api.order.boundary;

import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import science.monke.api.order.control.OrderService;
import science.monke.api.order.entity.NewOrderDTO;
import science.monke.api.order.entity.OrderDTO;

import java.util.List;
import java.util.UUID;

@Tag(name = "Orders")
@RestController
@RequestMapping("/orders")
public class OrderAPI {
  private final OrderService orderService;

  @Autowired
  public OrderAPI(final OrderService orderService) {
    this.orderService = orderService;
  }

  @GetMapping
  @ApiResponse(
      responseCode = "200",
      content = @Content(array = @ArraySchema(schema = @Schema(implementation = OrderDTO.class))))
  public Mono<List<OrderDTO>> getOrders() {
    return Mono.just(
        List.of(
            OrderDTO.builder().orderId(UUID.randomUUID()).build(),
            OrderDTO.builder().orderId(UUID.randomUUID()).build()));
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  @ApiResponse(
      responseCode = "201",
      content = @Content(schema = @Schema(implementation = OrderDTO.class)))
  public Mono<OrderDTO> postOrder(@RequestParam final NewOrderDTO newOrder) {
    return Mono.just(OrderDTO.builder().orderId(UUID.randomUUID()).build());
  }

  @GetMapping("/{orderId}")
  @ApiResponse(
      responseCode = "200",
      content = @Content(schema = @Schema(implementation = OrderDTO.class)))
  public Mono<OrderDTO> getOrder(@PathVariable(value = "orderId") final int orderId) {
    return Mono.just(OrderDTO.builder().orderId(UUID.randomUUID()).build());
  }
}
