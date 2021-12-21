package science.monke.api.order.entity;

import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.UUID;

@Getter
@Builder
public class OrderDTO {
  private OrderStatusDTO status;
  private UUID orderId;
  private List<OrderItemDTO> items;
}
