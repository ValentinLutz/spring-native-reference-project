package science.monke.internal.order.entity

import science.monke.internal.order.control.OrderIdConverter
import science.monke.internal.util.AbstractEntity
import java.time.OffsetDateTime
import javax.persistence.*

@Entity
@Table(name = "\"order\"", schema = "spring_native_reference_project")
class OrderEntity(
    @Column(name = "creation_date")
    var creationDate: OffsetDateTime,

    @Convert(converter = OrderIdConverter::class)
    @Column(name = "order_id")
    var orderId: OrderId,

    @Column(name = "order_status")
    var orderStatus: String,

    @OneToMany(mappedBy = "order", cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    var orderItems: Set<OrderItemEntity>
) : AbstractEntity<Int>()
