package science.monke.api.order.entity;

import lombok.Builder;

public record OrderItemDTO(
        String name
) {
    @Builder
    public OrderItemDTO{}
}
