package science.monke.util.task

import org.springframework.stereotype.Component
import science.monke.util.workflow.Context

@Component
class ShippingTask() : Task() {

    override fun getName(): TaskName {
        return TaskName.SHIPPING_TASK
    }

    override fun execute(context: Context): Context {
        log.info("Execute action ${getName()} for order id ${context.order.id}")

//        val taskLogEntity = TaskLogEntity(taskName = getName(), orderId = context.order.id, data = "WTF")
//        taskLogRepository.save(taskLogEntity)

        return context
    }
}
