package science.monke.util.task

import org.springframework.stereotype.Component
import science.monke.util.task.repository.TaskLogRepository
import science.monke.util.workflow.Context

@Component
class BillingTask(val taskLogRepository: TaskLogRepository) : Task() {

    override fun getName(): TaskName {
        return TaskName.BILLING_TASK
    }

    override fun execute(context: Context): Context {
        log.info("Execute action ${getName()} for order id ${context.order.id}")

//        val taskLogEntity = TaskLogEntity(taskName = getName(), order = context.order, data = "WTF")
//        taskLogRepository.save(taskLogEntity)

        return context
    }
}
