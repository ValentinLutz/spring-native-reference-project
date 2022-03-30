package science.monke.internal.order.control

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import science.monke.spring.properties.Environment
import science.monke.spring.properties.Region
import java.time.OffsetDateTime

internal class OrderIdGeneratorTest {
    private val orderIdGenerator = OrderIdGenerator()

    @Test
    fun `Generate order id for region none and environment dev`() {
        // GIVEN
        val offsetDateTime: OffsetDateTime = OffsetDateTime.parse("1980-01-01T00:00:00+00:00")
        // WHEN
        val orderId: String = orderIdGenerator.of(Region.NONE, Environment.DEV, offsetDateTime, "1")
        // THEN
        Assertions.assertEquals("Y2FvghhpMxG-NONE-DEV-qzefk8Ziv0A", orderId)
    }

    @Test
    fun `Generate order id for region eu and environment test`() {
        // GIVEN
        val offsetDateTime: OffsetDateTime = OffsetDateTime.parse("1980-01-01T00:00:00+00:00")
        // WHEN
        val orderId: String = orderIdGenerator.of(Region.EU, Environment.TEST, offsetDateTime, "101")
        // THEN
        Assertions.assertEquals("vMEFd4wLNhq-EU-TEST-RXrbUU60ZMw", orderId)
    }

    @Test
    fun `Generate order id for region eu and environment container`() {
        // GIVEN
        val offsetDateTime: OffsetDateTime = OffsetDateTime.parse("1980-01-01T00:00:00+00:00")
        // WHEN
        val orderId: String = orderIdGenerator.of(Region.EU, Environment.CONTAINER, offsetDateTime, "1")
        // THEN
        Assertions.assertEquals("lzYTQAmOP1m-EU-CONTAINER-nEkgfU1xzbg", orderId)
    }

    @Test
    fun `Generate order id for region us and environment e2e`() {
        // GIVEN
        val offsetDateTime: OffsetDateTime = OffsetDateTime.parse("1980-01-01T00:00:00+00:00")
        // WHEN
        val orderId: String = orderIdGenerator.of(Region.US, Environment.E2E, offsetDateTime, "101")
        // THEN
        Assertions.assertEquals("Fytr3CXdPUe-US-E2E-CGzYlcqUH4g", orderId)
    }

    @Test
    fun `Generate order id for region eu and environment prod`() {
        // GIVEN
        val offsetDateTime: OffsetDateTime = OffsetDateTime.parse("1980-01-01T00:00:00+00:00")
        // WHEN
        val orderId: String = orderIdGenerator.of(Region.EU, Environment.PROD, offsetDateTime, "10101")
        // THEN
        Assertions.assertEquals("mtQYw0tBMna-EU-p8zaGDvZEGw", orderId)
    }

    @Test
    fun `Generate test order ids for region none and environment dev`() {
        val offsetDateTime: OffsetDateTime = OffsetDateTime.parse("1980-01-01T00:00:00+00:00")
        val salts = setOf("1", "101", "10101", "1010101")
        salts.forEach { salt -> println(orderIdGenerator.of(Region.NONE, Environment.DEV, offsetDateTime, salt)) }
    }

    @Test
    fun `Generate test order ids for region eu and environment test`() {
        val offsetDateTime: OffsetDateTime = OffsetDateTime.parse("1980-01-01T00:00:00+00:00")
        val salts = setOf("1", "101", "10101", "1010101")
        salts.forEach { salt -> println(orderIdGenerator.of(Region.EU, Environment.TEST, offsetDateTime, salt)) }
    }
}