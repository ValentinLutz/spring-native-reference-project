package science.monke.api.order.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import science.monke.api.order.entity.OrderDTO;
import science.monke.internal.order.entity.OrderEntity;
import science.monke.internal.order.repository.OrderRepository;

import java.util.UUID;

@Service
public class OrderService {

  private final OrderRepository orderRepository;

  @Autowired
  public OrderService(final OrderRepository orderRepository) {
    this.orderRepository = orderRepository;
  }

  public Flux<OrderDTO> getOrders() {
    return orderRepository.findAll().map(this::map);
  }

  public Mono<OrderDTO> getOrder(final UUID uuid) {
    return orderRepository.findByOrderId(uuid).map(this::map);
  }

  public OrderDTO map(final OrderEntity orderEntity) {
    return OrderDTO.builder().orderId(orderEntity.getOrderId()).build();
  }
}
