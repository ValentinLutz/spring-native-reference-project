package science.monke.internal.order.entity

import science.monke.util.AbstractEntity
import java.time.OffsetDateTime
import javax.persistence.*

@Entity
@Table(name = "order_item")
class OrderItemEntity(
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "order_id", referencedColumnName = "order_id")
    var order: OrderEntity,

    @Column(name = "creation_date")
    val creationDate: OffsetDateTime = OffsetDateTime.now(),

    @Column(name = "item_name")
    var itemName: String
) : AbstractEntity<Int>()
