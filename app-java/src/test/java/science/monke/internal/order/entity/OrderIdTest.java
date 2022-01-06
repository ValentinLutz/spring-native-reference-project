package science.monke.internal.order.entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import science.monke.spring.config.Environment;
import science.monke.spring.config.Region;

import java.time.OffsetDateTime;

class OrderIdTest {

  @Test
  void generateOrderIdEven() {
    // GIVEN

    // WHEN
    final OrderId orderId = OrderId.randomOrderId(Region.EU, Environment.DEV);
    // THEN
    Assertions.assertEquals(32, orderId.value().length());
  }

  @Test
  void generateOrderIdOdd() {
    // GIVEN

    // WHEN
    final OrderId orderId = OrderId.randomOrderId(Region.EU, Environment.TEST);
    // THEN
    Assertions.assertEquals(32, orderId.value().length());
  }

  @Test
  void generateOrderIdEu() {
    // GIVEN
    final OffsetDateTime offsetDateTime = OffsetDateTime.parse("1980-01-01T00:00:00+00:00");
    // WHEN
    final OrderId orderId =
        OrderId.generateOrderId(Region.EU, Environment.CONTAINER, offsetDateTime, "101");
    // THEN
    Assertions.assertEquals("c205a31a6-EU-CONTAINER-bad76db30", orderId.value());
  }

  @Test
  void generateOrderIdUs() {
    // GIVEN
    final OffsetDateTime offsetDateTime = OffsetDateTime.parse("1980-01-01T00:00:00+00:00");
    // WHEN
    final OrderId orderId =
        OrderId.generateOrderId(Region.US, Environment.E2E, offsetDateTime, "10101");
    // THEN
    Assertions.assertEquals("302659a38d0f-US-E2E-b475b5357cac", orderId.value());
  }

  @Test
  void generateOrderIdEuProd() {
    // GIVEN
    final OffsetDateTime offsetDateTime = OffsetDateTime.parse("1980-01-01T00:00:00+00:00");
    // WHEN
    final OrderId orderId =
        OrderId.generateOrderId(Region.EU, Environment.PROD, offsetDateTime, "1010101");
    // THEN
    Assertions.assertEquals("18c02989663d34-EU-88ac83d565073c", orderId.value());
  }
}
