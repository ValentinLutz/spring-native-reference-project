package science.monke.internal.order.entity

import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import science.monke.internal.util.AbstractEntity
import java.time.OffsetDateTime

@Table("spring_native_reference_project.order_item")
class OrderItemEntity(
    @Column("creation_date")
    var creationDate: OffsetDateTime,

    @Column("item_name")
    var itemName: String,

    @Column("order_id")
    var orderId: String
) : AbstractEntity<Int>()
