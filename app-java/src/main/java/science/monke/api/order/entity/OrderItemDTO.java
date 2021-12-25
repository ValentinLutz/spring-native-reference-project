package science.monke.api.order.entity;

import lombok.Builder;

@Builder
public record OrderItemDTO(
        String name
) {}
