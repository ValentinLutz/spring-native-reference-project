package science.monke.api.order.entity;

import lombok.Builder;

import java.util.Set;

public record OrderRequest(
        Set<String> items
) {
    // Can be moved to class level if IntelliJ plugin works
    @Builder
    public OrderRequest {}
}
