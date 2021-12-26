package science.monke.api.order.entity;

import lombok.Builder;

import java.util.List;
import java.util.UUID;

public record OrderDTO(
        OrderStatusDTO status,
        UUID orderId,
        List<OrderItemDTO> items
) {
    // Can be moved to class level if IntelliJ plugin works
    @Builder
    public OrderDTO{}
}
