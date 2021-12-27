package science.monke.internal.workflow;

import lombok.Builder;
import lombok.Data;
import science.monke.internal.order.entity.OrderEntity;
import science.monke.internal.order.entity.OrderItemEntity;

import java.util.List;

@Data
@Builder
public class WorkflowObject {
  private OrderEntity order;
  private List<OrderItemEntity> orderItems;
}
