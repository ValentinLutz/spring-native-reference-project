package science.monke.internal.order.entity

import science.monke.spring.config.Environment
import science.monke.spring.config.Region
import java.io.Serializable
import java.nio.charset.StandardCharsets
import java.security.SecureRandom
import java.time.OffsetDateTime
import java.util.*

data class OrderId(val value: String) : Serializable {

    companion object {
        fun randomOrderId(region: Region, environment: Environment): OrderId {
            return generateOrderId(region, environment, OffsetDateTime.now(), randomSalt())
        }

        fun generateOrderId(
            region: Region,
            environment: Environment,
            offsetDateTime: OffsetDateTime,
            salt: String
        ): OrderId {
            val bytes: ByteArray =
                (region.name + environment.name + offsetDateTime + salt).toByteArray(StandardCharsets.UTF_8)
            val uuidWithoutHyphen: String = UUID.nameUUIDFromBytes(bytes).toString().replace("-", "")

            var identifier = "-$region-$environment-"
            if (environment == Environment.PROD) {
                identifier = "-$region-"
            }

            val orderIdLength = 32
            val length: Int = (orderIdLength - identifier.length) / 2
            var orderId: String = uuidWithoutHyphen.substring(0, length) + identifier + uuidWithoutHyphen.substring(16)
            orderId = orderId.substring(0, orderIdLength)

            return OrderId(orderId)
        }

        private fun randomSalt(): String {
            val byteArray = ByteArray(4)
            SecureRandom.getInstanceStrong().nextBytes(byteArray)
            return byteArray.contentToString()
        }
    }

}
