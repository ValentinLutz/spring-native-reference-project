package science.monke.internal.event;

import science.monke.internal.workflow.WorkflowObject;

public interface Event {
  EventName getName();

  WorkflowObject execute(WorkflowObject workflowObject);
}
