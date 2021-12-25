package science.monke.api.order.entity;

import lombok.Builder;

import java.util.List;

@Builder
public record NewOrderDTO(
        List<String> items
) {}
