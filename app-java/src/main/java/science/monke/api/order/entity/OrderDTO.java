package science.monke.api.order.entity;

import lombok.Builder;

import java.util.List;
import java.util.UUID;

@Builder
public record OrderDTO(
        OrderStatusDTO status,
        UUID orderId,
        List<OrderItemDTO> items
) {}
