package science.monke.internal.order.repository;

import org.springframework.data.repository.CrudRepository;
import science.monke.internal.order.entity.OrderItemEntity;

import java.util.Set;
import java.util.UUID;

public interface OrderItemRepository extends CrudRepository<OrderItemEntity, Long> {
  Set<OrderItemEntity> findByOrderId(UUID orderId);
}
