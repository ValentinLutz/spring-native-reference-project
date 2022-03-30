package science.monke.internal.order.control

import org.springframework.data.relational.core.mapping.event.BeforeConvertCallback
import org.springframework.stereotype.Component
import science.monke.internal.order.entity.OrderEntity
import science.monke.spring.properties.CustomProperties

@Component
class OrderEntityCallback(
    private val customProperties: CustomProperties,
    private val orderIdGenerator: OrderIdGenerator
) : BeforeConvertCallback<OrderEntity> {
    override fun onBeforeConvert(orderEntity: OrderEntity): OrderEntity {
        if (orderEntity.id == null) {
            orderEntity.id = orderIdGenerator.random(customProperties.region, customProperties.environment)
        }
        return orderEntity
    }
}