package science.monke.util.workflow

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import science.monke.util.task.Task

abstract class Workflow(
    private val workflowName: WorkflowName,
    private val tasks: Set<Task>
) {

    private val logger: Logger = LoggerFactory.getLogger(this::class.java)

    private fun getName(): WorkflowName {
        return workflowName
    }

    fun getWorkflowLog() {

    }

    fun execute(context: Context) {
        logger.info("Execute workflow ${getName()} for order id ${context.order.id}")
        tasks.map { event -> event.execute(context) }
    }
}
