package science.monke.internal.order.converter

import org.springframework.core.convert.converter.Converter
import org.springframework.data.convert.ReadingConverter
import science.monke.internal.order.entity.OrderStatus

@ReadingConverter
class StringToOrderStatusConverter : Converter<String, OrderStatus> {
    override fun convert(string: String): OrderStatus {
        return OrderStatus.valueOf(string.uppercase())
    }
}