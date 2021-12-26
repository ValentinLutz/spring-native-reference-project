package science.monke.api.order.entity;

import lombok.Builder;

public record OrderItemResponse(
        String name
) {
    // Can be moved to class level if IntelliJ plugin works
    @Builder
    public OrderItemResponse {}
}
