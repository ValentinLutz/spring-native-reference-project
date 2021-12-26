package science.monke.internal.order.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;
import science.monke.internal.order.entity.OrderEntity;

import java.util.UUID;

public interface OrderRepository extends ReactiveCrudRepository<OrderEntity, Long> {
  Mono<OrderEntity> findByOrderId(UUID orderId);
}
