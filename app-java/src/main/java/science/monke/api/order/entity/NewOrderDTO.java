package science.monke.api.order.entity;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class NewOrderDTO {
  private List<String> items;
}
