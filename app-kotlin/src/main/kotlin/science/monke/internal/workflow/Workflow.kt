package science.monke.internal.workflow

interface Workflow {

    fun getName(): WorkflowName
    
    fun execute(workflowObject: WorkflowObject): WorkflowObject
}
