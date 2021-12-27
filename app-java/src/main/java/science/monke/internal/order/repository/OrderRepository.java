package science.monke.internal.order.repository;

import org.springframework.data.repository.CrudRepository;
import science.monke.internal.order.entity.OrderEntity;

import java.util.Optional;
import java.util.UUID;

public interface OrderRepository extends CrudRepository<OrderEntity, Long> {
  Optional<OrderEntity> findByOrderId(UUID orderId);
}
