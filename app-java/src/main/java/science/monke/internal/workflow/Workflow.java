package science.monke.internal.workflow;

public interface Workflow {

  WorkflowName getName();

  WorkflowObject execute(WorkflowObject workflowObject);
}
