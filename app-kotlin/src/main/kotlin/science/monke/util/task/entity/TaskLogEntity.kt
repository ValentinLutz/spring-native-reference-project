package science.monke.util.task.entity

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import science.monke.util.task.TaskName
import java.time.OffsetDateTime

@Table("task_log")
class TaskLogEntity(
    @Id
    var id: Int? = null,

    @Column("order_id")
    val orderId: String,

    @Column("task")
    val taskName: TaskName,

    @Column("creation_date")
    val creationDate: OffsetDateTime = OffsetDateTime.now(),

    @Column("data")
    val data: String? = null
)