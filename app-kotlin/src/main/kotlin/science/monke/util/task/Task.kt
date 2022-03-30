package science.monke.util.task

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import science.monke.util.workflow.Context

abstract class Task {

    protected val log: Logger = LoggerFactory.getLogger(this::class.java)

    abstract fun getName(): TaskName

    abstract fun execute(context: Context): Context
}
