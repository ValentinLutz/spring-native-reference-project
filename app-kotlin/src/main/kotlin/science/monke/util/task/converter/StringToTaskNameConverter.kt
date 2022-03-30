package science.monke.util.task.converter

import org.springframework.core.convert.converter.Converter
import org.springframework.data.convert.ReadingConverter
import science.monke.util.task.TaskName

@ReadingConverter
class StringToTaskNameConverter : Converter<String, TaskName> {
    override fun convert(string: String): TaskName {
        return TaskName.valueOf(string.uppercase())
    }
}