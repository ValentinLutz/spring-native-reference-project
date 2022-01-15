package science.monke.internal.order.entity;

import science.monke.internal.util.AbstractEntity
import java.time.OffsetDateTime
import javax.persistence.*

@Entity
@Table(name = "order_item", schema = "spring_native_reference_project")
class OrderItemEntity(

    @Column(name = "creation_date")
    var creationDate: OffsetDateTime,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", referencedColumnName = "order_id")
    var order: OrderEntity,

    @Column(name = "item_name")
    var itemName: String
) : AbstractEntity<Int>()
