package science.monke.internal.order.entity

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import java.time.OffsetDateTime

@Table("order_item")
class OrderItemEntity(
    @Id
    var id: Int? = null,

    @Column("order_id")
    val orderId: String? = null,

    @Column("creation_date")
    val creationDate: OffsetDateTime = OffsetDateTime.now(),

    @Column("item_name")
    val itemName: String
)
