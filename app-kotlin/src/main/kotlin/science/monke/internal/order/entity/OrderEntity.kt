package science.monke.internal.order.entity

import science.monke.util.AbstractEntity
import java.time.OffsetDateTime
import javax.persistence.*

@Entity
@Table(name = "\"order\"")
class OrderEntity(
    @Column(name = "order_id")
    var orderId: OrderId,

    @Column(name = "creation_date")
    var creationDate: OffsetDateTime,

    @Column(name = "order_status")
    var orderStatus: String,

    @OneToMany(mappedBy = "order", fetch = FetchType.EAGER, cascade = [CascadeType.ALL])
    var orderItems: Set<OrderItemEntity>,
) : AbstractEntity<Int>()
