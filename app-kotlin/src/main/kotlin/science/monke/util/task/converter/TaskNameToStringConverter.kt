package science.monke.util.task.converter

import org.springframework.core.convert.converter.Converter
import org.springframework.data.convert.WritingConverter
import science.monke.util.task.TaskName

@WritingConverter
class TaskNameToStringConverter : Converter<TaskName, String> {
    override fun convert(taskName: TaskName): String {
        return taskName.name.lowercase()
    }
}