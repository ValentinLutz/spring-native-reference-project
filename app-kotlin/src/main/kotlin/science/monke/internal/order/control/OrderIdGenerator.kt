package science.monke.internal.order.control

import org.springframework.stereotype.Component
import science.monke.spring.properties.Environment
import science.monke.spring.properties.Region
import java.nio.ByteBuffer
import java.nio.charset.StandardCharsets
import java.security.SecureRandom
import java.time.OffsetDateTime
import java.util.*

@Component
class OrderIdGenerator {
    fun random(region: Region, environment: Environment): String {
        return of(region, environment, OffsetDateTime.now(), randomSalt())
    }

    fun of(region: Region, environment: Environment, offsetDateTime: OffsetDateTime, salt: String): String {
        val bytes: ByteArray =
            (region.name + environment.name + offsetDateTime + salt).toByteArray(StandardCharsets.UTF_8)
        val uuid: UUID = UUID.nameUUIDFromBytes(bytes)
        val bas64UUID: String = uuidToBase64(uuid)

        var identifier = "-$region-$environment-"
        if (environment == Environment.PROD) {
            identifier = "-$region-"
        }

        val bas64UUIDLength: Int = bas64UUID.length / 2

        return bas64UUID.substring(0, bas64UUIDLength) + identifier + bas64UUID.substring(bas64UUIDLength)
    }

    private fun randomSalt(): String {
        val byteArray = ByteArray(4)
        SecureRandom.getInstanceStrong().nextBytes(byteArray)
        return byteArray.contentToString()
    }

    private fun uuidToBase64(uuid: UUID): String {
        val base64UUID = Base64.getUrlEncoder().encodeToString(uuid.toBytes())
        return base64UUID.replace('-', '!').replace('_', '*').substring(0, base64UUID.length - 2)
    }

    private fun UUID.toBytes(): ByteArray {
        val byteBuffer = ByteBuffer.wrap(ByteArray(16))
        byteBuffer.putLong(mostSignificantBits)
        byteBuffer.putLong(leastSignificantBits)
        return byteBuffer.array()
    }
}

