package science.monke.api.order.entity;

import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class NewOrderDTO {

  private List<String> items;

}
