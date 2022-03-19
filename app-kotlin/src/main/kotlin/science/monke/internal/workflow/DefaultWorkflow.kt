package science.monke.internal.workflow

import org.springframework.stereotype.Component
import science.monke.internal.event.Event

@Component
class DefaultWorkflow(val orderEvent: Event) : Workflow {

    override fun getName(): WorkflowName {
        return WorkflowName.DEFAULT_WORKFLOW
    }

    override fun execute(workflowObject: WorkflowObject): WorkflowObject {
        return orderEvent.execute(workflowObject)
    }
}
