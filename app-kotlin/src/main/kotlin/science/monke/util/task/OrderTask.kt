package science.monke.util.task

import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import science.monke.internal.order.repository.OrderRepository
import science.monke.util.task.entity.TaskLogEntity
import science.monke.util.task.repository.TaskLogRepository
import science.monke.util.workflow.Context

@Component
class OrderTask(
    private val orderRepository: OrderRepository,
    private val taskLogRepository: TaskLogRepository
) : Task() {

    override fun getName(): TaskName {
        return TaskName.ORDER_TASK
    }

    @Transactional
    override fun execute(context: Context): Context {
        orderRepository.save(context.order)
        log.info("Execute action ${getName()} for order id ${context.order.id}")

        val taskLogEntity = TaskLogEntity(taskName = getName(), orderId = context.order.id.orEmpty(), data = "WTF")
        taskLogRepository.save(taskLogEntity)

        return context
    }
}
