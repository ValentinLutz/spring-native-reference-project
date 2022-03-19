package science.monke.internal.workflow

interface Workflow {

    suspend fun getName(): WorkflowName

    suspend fun execute(workflowObject: WorkflowObject): WorkflowObject
}
