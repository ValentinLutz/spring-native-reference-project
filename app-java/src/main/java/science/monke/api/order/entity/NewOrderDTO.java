package science.monke.api.order.entity;

import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
@Builder
public class NewOrderDTO {
  List<String> items;
}
