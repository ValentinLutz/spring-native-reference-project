package science.monke.internal.order.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import science.monke.internal.order.entity.OrderEntity;
import science.monke.internal.order.entity.OrderItemEntity;

import java.util.UUID;

public interface OrderItemRepository extends ReactiveCrudRepository<OrderItemEntity, Long> {
  Flux<OrderEntity> findByOrderId(UUID orderId);
}
