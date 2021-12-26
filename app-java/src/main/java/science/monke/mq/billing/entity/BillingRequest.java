package science.monke.mq.billing.entity;

import lombok.Builder;

public record BillingRequest(BillingStatus billingStatus) {
    // Can be moved to class level if IntelliJ plugin works
    @Builder
    public BillingRequest{}
}
