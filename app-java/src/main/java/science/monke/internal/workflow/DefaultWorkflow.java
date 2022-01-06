package science.monke.internal.workflow;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import science.monke.internal.event.Event;

@Component
public class DefaultWorkflow implements Workflow {

  private final Event orderEvent;

  @Autowired
  public DefaultWorkflow(final Event orderEvent) {
    this.orderEvent = orderEvent;
  }

  @Override
  public WorkflowName getName() {
    return WorkflowName.DEFAULT_WORKFLOW;
  }

  @Override
  public WorkflowObject execute(final WorkflowObject workflowObject) {
    return orderEvent.execute(workflowObject);
  }
}
