package science.monke.internal.event;

import science.monke.internal.workflow.WorkflowObject;

interface Event {
    fun getName(): EventName
    fun execute(workflowObject: WorkflowObject): WorkflowObject
}
