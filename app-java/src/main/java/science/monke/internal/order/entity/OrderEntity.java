package science.monke.internal.order.entity;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.OffsetDateTime;
import java.util.UUID;

@Data
@Builder
@Table(value = "\"order\"")
public class OrderEntity {
  @Id private long id;

  @Column(value = "creation_date")
  private OffsetDateTime creationDate;

  @Column(value = "order_id")
  private UUID orderId;
}
