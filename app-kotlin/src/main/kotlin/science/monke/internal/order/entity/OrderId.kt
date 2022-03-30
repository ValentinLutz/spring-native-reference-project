package science.monke.internal.order.entity

import java.io.Serializable

@JvmInline
value class OrderId(private val value: String) : Serializable {
    override fun toString(): String {
        return value
    }
}