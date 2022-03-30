package science.monke.internal.order.converter

import org.springframework.core.convert.converter.Converter
import org.springframework.data.convert.ReadingConverter
import science.monke.util.workflow.WorkflowName

@ReadingConverter
class StringToWorkflowNameConverter : Converter<String, WorkflowName> {
    override fun convert(string: String): WorkflowName {
        return WorkflowName.valueOf(string.uppercase())
    }
}