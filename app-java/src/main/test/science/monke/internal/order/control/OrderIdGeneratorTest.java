package science.monke.internal.order.control;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import science.monke.internal.order.entity.Region;
import science.monke.spring.config.CustomProperties;

import java.time.OffsetDateTime;

@ExtendWith(MockitoExtension.class)
class OrderIdGeneratorTest {

  @InjectMocks private OrderIdGenerator orderIdGenerator;
  @Mock private CustomProperties customProperties;

  @Test
  void generateOrderId() {
    // GIVEN
    // WHEN
    Mockito.doReturn(Region.EU).when(customProperties).getRegion();
    final String orderId = orderIdGenerator.generateOrderId();
    // THEN
    Assertions.assertEquals(20, orderId.length());
  }

  @Test
  void generateOrderIdEU() {
    // GIVEN
    final OffsetDateTime offsetDateTime = OffsetDateTime.parse("1980-01-01T00:00:00+00:00");
    // WHEN
    Mockito.doReturn(Region.EU).when(customProperties).getRegion();
    final String orderId = orderIdGenerator.generateOrderId(offsetDateTime, "101");
    // THEN
    Assertions.assertEquals(20, orderId.length());
    Assertions.assertEquals("e7491f32-EU-a0b7db65", orderId);
  }

  @Test
  void generateOrderIdUS() {
    // GIVEN
    final OffsetDateTime offsetDateTime = OffsetDateTime.parse("1980-01-01T00:00:00+00:00");
    // WHEN
    Mockito.doReturn(Region.US).when(customProperties).getRegion();
    final String orderId = orderIdGenerator.generateOrderId(offsetDateTime, "10101");
    // THEN
    Assertions.assertEquals(20, orderId.length());
    Assertions.assertEquals("e06e56a6-US-deb7927c", orderId);
  }

  @Test
  void generateRandomString() {
    // GIVEN
    // WHEN
    final String randomString = orderIdGenerator.generateRandomString();
    // THEN
    Assertions.assertEquals(8, randomString.length());
  }
}
