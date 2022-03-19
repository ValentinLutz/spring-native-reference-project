package science.monke.internal.event

import science.monke.internal.workflow.WorkflowObject

interface Event {
    suspend fun getName(): EventName
    suspend fun execute(workflowObject: WorkflowObject): WorkflowObject
}
