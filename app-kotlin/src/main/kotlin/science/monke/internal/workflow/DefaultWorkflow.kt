package science.monke.internal.workflow

import org.springframework.stereotype.Component
import science.monke.internal.event.Event

@Component
class DefaultWorkflow(val orderEvent: Event) : Workflow {

    override suspend fun getName(): WorkflowName {
        return WorkflowName.DEFAULT_WORKFLOW
    }

    override suspend fun execute(workflowObject: WorkflowObject): WorkflowObject {
        return orderEvent.execute(workflowObject)
    }
}
