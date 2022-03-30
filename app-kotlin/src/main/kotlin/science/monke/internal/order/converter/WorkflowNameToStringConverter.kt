package science.monke.internal.order.converter

import org.springframework.core.convert.converter.Converter
import org.springframework.data.convert.WritingConverter
import science.monke.util.workflow.WorkflowName

@WritingConverter
class WorkflowNameToStringConverter : Converter<WorkflowName, String> {
    override fun convert(workflowName: WorkflowName): String {
        return workflowName.name.lowercase()
    }
}