package science.monke.internal.order.control;

import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import science.monke.spring.config.CustomProperties;
import science.monke.spring.config.Environment;
import science.monke.spring.config.Region;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.time.OffsetDateTime;
import java.util.Random;

@Log4j2
@Component
public class OrderIdGenerator {

  private static final Random RANDOM = new Random();

  private final CustomProperties customProperties;

  @Autowired
  public OrderIdGenerator(final CustomProperties customProperties) {
    this.customProperties = customProperties;
  }

  public String generateOrderId() {
    return generateOrderId(OffsetDateTime.now(), generateRandomString());
  }

  @SneakyThrows
  public String generateOrderId(final OffsetDateTime offsetDateTime, final String salt) {
    final MessageDigest digest = MessageDigest.getInstance("SHA3-256");
    final Region region = customProperties.getRegion();
    final Environment environment = customProperties.getEnvironment();
    final String valueToHash = region.name() + environment.name() + offsetDateTime + salt;
    final byte[] hashBytes = digest.digest((valueToHash).getBytes(StandardCharsets.UTF_8));
    final String sha3Hex = bytesToHex(hashBytes);
    final String orderId =
        sha3Hex.substring(0, 8)
            + "-"
            + region.name()
            + "-"
            + environment.name()
            + "-"
            + sha3Hex.substring(32, 40);
    log.info("Generated new order id: {}", orderId);
    return orderId;
  }

  public String generateRandomString() {
    final byte[] array = new byte[4];
    RANDOM.nextBytes(array);
    return bytesToHex(array);
  }

  private String bytesToHex(final byte[] hash) {
    final StringBuilder hexString = new StringBuilder(2 * hash.length);
    for (final byte b : hash) {
      final String hex = Integer.toHexString(0xff & b);
      if (hex.length() == 1) {
        hexString.append('0');
      }
      hexString.append(hex);
    }
    return hexString.toString();
  }
}
