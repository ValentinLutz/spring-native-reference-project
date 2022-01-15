package science.monke.internal.order.control;

import science.monke.internal.order.entity.OrderId;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
class OrderIdConverter : AttributeConverter<OrderId, String> {

    @Override
    override fun convertToDatabaseColumn(orderId: OrderId): String {
        return orderId.value
    }

    override fun convertToEntityAttribute(orderId: String): OrderId {
        return OrderId(orderId);
    }
}
