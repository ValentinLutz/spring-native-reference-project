package science.monke.api.order.entity;

import lombok.Builder;
import lombok.Value;

import java.util.List;
import java.util.UUID;

@Value
@Builder
public class OrderDTO {
  OrderStatusDTO status;
  UUID orderId;
  List<OrderItemDTO> items;
}
