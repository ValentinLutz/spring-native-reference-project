package science.monke.internal.order.entity;

import lombok.*;
import science.monke.internal.order.control.OrderIdConverter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.Set;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "\"order\"", schema = "spring_native_reference_project")
public class OrderEntity implements Serializable {
  @EqualsAndHashCode.Include
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @Column(name = "creation_date", nullable = false)
  private OffsetDateTime creationDate;

  @Convert(converter = OrderIdConverter.class)
  @Column(name = "order_id", nullable = false)
  private OrderId orderId;

  @Column(name = "order_status", nullable = false)
  private String orderStatus;

  @ToString.Exclude
  @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  private Set<OrderItemEntity> orderItems;
}
