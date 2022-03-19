package science.monke.internal.order.entity

import org.springframework.data.annotation.Transient
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import science.monke.internal.util.AbstractEntity
import java.time.OffsetDateTime

@Table("spring_native_reference_project.\"order\"")
class OrderEntity(
    @Column("creation_date")
    var creationDate: OffsetDateTime,

    @Column("order_id")
    var orderId: String,

    @Column("order_status")
    var orderStatus: String,
) : AbstractEntity<Int>() {
    @Transient
    var orderItems: Set<OrderItemEntity> = HashSet()
}
