package science.monke.internal.order.converter

import org.springframework.core.convert.converter.Converter
import org.springframework.data.convert.WritingConverter
import science.monke.internal.order.entity.OrderStatus

@WritingConverter
class OrderStatusToStringConverter : Converter<OrderStatus, String> {
    override fun convert(orderStatus: OrderStatus): String {
        return orderStatus.name.lowercase()
    }
}