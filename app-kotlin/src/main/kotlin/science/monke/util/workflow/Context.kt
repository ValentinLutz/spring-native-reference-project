package science.monke.util.workflow

import science.monke.internal.order.entity.OrderEntity
import science.monke.util.task.entity.TaskLogEntity

data class Context(
    var order: OrderEntity,
    var taskLogs: Set<TaskLogEntity> = mutableSetOf()
)
