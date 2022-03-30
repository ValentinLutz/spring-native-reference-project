package science.monke.util.task.entity

import science.monke.internal.order.entity.OrderId
import science.monke.util.AbstractEntity
import science.monke.util.task.TaskName
import java.time.OffsetDateTime
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Table

@Entity
@Table(name = "task_log")
class TaskLogEntity(
    @Column(name = "order_id")
    val orderId: OrderId,

    @Column(name = "creation_date")
    val creationDate: OffsetDateTime = OffsetDateTime.now(),

    @Column(name = "task")
    val taskName: TaskName,

    @Column(name = "data")
    var data: String
) : AbstractEntity<Int>()