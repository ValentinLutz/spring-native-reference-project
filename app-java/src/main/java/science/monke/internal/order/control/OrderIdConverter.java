package science.monke.internal.order.control;

import science.monke.internal.order.entity.OrderId;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class OrderIdConverter implements AttributeConverter<OrderId, String> {

  @Override
  public String convertToDatabaseColumn(final OrderId orderId) {
    return orderId.value();
  }

  @Override
  public OrderId convertToEntityAttribute(final String orderId) {
    return new OrderId(orderId);
  }
}
