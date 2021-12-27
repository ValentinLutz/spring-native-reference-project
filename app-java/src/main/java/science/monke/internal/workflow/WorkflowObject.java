package science.monke.internal.workflow;

import lombok.Builder;
import lombok.Data;
import science.monke.internal.order.entity.OrderEntity;

@Data
@Builder
public class WorkflowObject {
  private OrderEntity order;
}
