package science.monke.api.order.boundary;

import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import science.monke.api.order.control.OrderService;
import science.monke.api.order.entity.OrderRequest;
import science.monke.api.order.entity.OrderResponse;
import science.monke.spring.exceptions.HttpNotFound;

import java.util.UUID;

@Tag(name = "order")
@RestController
@RequestMapping("/orders")
public class OrderController {
  private final OrderService orderService;

  @Autowired
  public OrderController(final OrderService orderService) {
    this.orderService = orderService;
  }

  @GetMapping
  @ApiResponse(
      responseCode = "200",
      content =
          @Content(array = @ArraySchema(schema = @Schema(implementation = OrderResponse.class))))
  public Flux<OrderResponse> getOrders() {
    return orderService.getOrders().switchIfEmpty(Flux.error(HttpNotFound::new));
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  @ApiResponse(
      responseCode = "201",
      content = @Content(schema = @Schema(implementation = OrderResponse.class)))
  public Mono<OrderResponse> postOrder(@RequestParam final OrderRequest orderRequest) {
    return Mono.empty();
  }

  @GetMapping("/{orderId}")
  @ApiResponse(
      responseCode = "200",
      content = @Content(schema = @Schema(implementation = OrderResponse.class)))
  public Mono<OrderResponse> getOrder(@PathVariable final UUID orderId) {
    return orderService.getOrder(orderId).switchIfEmpty(Mono.error(HttpNotFound::new));
  }
}
