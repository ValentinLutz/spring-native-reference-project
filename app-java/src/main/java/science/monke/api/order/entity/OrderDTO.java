package science.monke.api.order.entity;

import java.util.List;
import java.util.UUID;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class OrderDTO {

  private OrderStatusDTO status;

  private UUID orderId;

  private List<OrderItemDTO> items;

}
