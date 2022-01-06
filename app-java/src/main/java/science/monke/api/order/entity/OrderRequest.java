package science.monke.api.order.entity;

import lombok.Builder;

import java.util.List;

public record OrderRequest(
        List<String> items
) {
    // Can be moved to class level if IntelliJ plugin works
    @Builder
    public OrderRequest {}
}
