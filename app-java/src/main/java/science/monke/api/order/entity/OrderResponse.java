package science.monke.api.order.entity;

import lombok.Builder;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

public record OrderResponse(
        OrderStatus status,
        UUID orderId,
        List<OrderItemResponse> items,
        OffsetDateTime creationDate
) {
    // Can be moved to class level if IntelliJ plugin works
    @Builder
    public OrderResponse {}
}
