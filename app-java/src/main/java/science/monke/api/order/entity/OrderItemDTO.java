package science.monke.api.order.entity;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class OrderItemDTO {
  String name;
}
