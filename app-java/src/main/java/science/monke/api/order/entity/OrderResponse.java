package science.monke.api.order.entity;

import lombok.Builder;
import science.monke.internal.order.entity.OrderId;

import java.time.OffsetDateTime;
import java.util.Set;

public record OrderResponse(
        OrderStatus status,
        OrderId orderId,
        Set<OrderItemResponse> items,
        OffsetDateTime creationDate
) {
    // Can be moved to class level if IntelliJ plugin works
    @Builder
    public OrderResponse {}
}
