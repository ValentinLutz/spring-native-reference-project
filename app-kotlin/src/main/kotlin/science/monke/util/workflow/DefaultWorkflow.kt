package science.monke.util.workflow

import org.springframework.stereotype.Component
import science.monke.util.task.BillingTask
import science.monke.util.task.OrderTask
import science.monke.util.task.ShippingTask

@Component
class DefaultWorkflow(
    orderAction: OrderTask,
    billingAction: BillingTask,
    shippingAction: ShippingTask
) : Workflow(
    WorkflowName.DEFAULT_WORKFLOW,
    setOf(
        orderAction,
        billingAction,
        shippingAction
    )
)
