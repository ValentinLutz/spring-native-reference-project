package science.monke.api.order.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import science.monke.api.order.entity.OrderResponse;
import science.monke.internal.order.repository.OrderRepository;

import java.util.UUID;

@Service
public class OrderService {

  private final OrderRepository orderRepository;
  private final OrderResponseMapper orderResponseMapper;

  @Autowired
  public OrderService(
      final OrderRepository orderRepository, final OrderResponseMapper orderResponseMapper) {
    this.orderRepository = orderRepository;
    this.orderResponseMapper = orderResponseMapper;
  }

  public Flux<OrderResponse> getOrders() {
    return orderRepository.findAll().map(orderResponseMapper::orderEntityToOrderResponse);
  }

  public Mono<OrderResponse> getOrder(final UUID uuid) {
    return orderRepository.findByOrderId(uuid).map(orderResponseMapper::orderEntityToOrderResponse);
  }
}
