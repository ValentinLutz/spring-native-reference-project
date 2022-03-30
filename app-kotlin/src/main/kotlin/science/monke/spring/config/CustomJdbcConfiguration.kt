package science.monke.spring.config

import org.springframework.context.annotation.Configuration
import org.springframework.data.jdbc.repository.config.AbstractJdbcConfiguration
import science.monke.internal.order.converter.OrderStatusToStringConverter
import science.monke.internal.order.converter.StringToOrderStatusConverter
import science.monke.util.task.converter.StringToTaskNameConverter
import science.monke.util.task.converter.TaskNameToStringConverter

@Configuration
class CustomJdbcConfiguration : AbstractJdbcConfiguration() {

    override fun userConverters(): MutableList<*> {
        return mutableListOf(
            OrderStatusToStringConverter(),
            StringToOrderStatusConverter(),
            TaskNameToStringConverter(),
            StringToTaskNameConverter()
        )
    }
}