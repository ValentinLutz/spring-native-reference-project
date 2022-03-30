package science.monke.internal.order.entity

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.MappedCollection
import org.springframework.data.relational.core.mapping.Table
import java.time.OffsetDateTime

@Table("order")
class OrderEntity(
    @Id
    var id: String? = null,

    @Column("creation_date")
    val creationDate: OffsetDateTime = OffsetDateTime.now(),

    @Column("order_status")
    var orderStatus: OrderStatus,

    @MappedCollection(idColumn = "order_id", keyColumn = "order_id")
    var orderItems: Set<OrderItemEntity>
)
