package science.monke.internal.order.entity;

import lombok.SneakyThrows;
import science.monke.spring.config.Environment;
import science.monke.spring.config.Region;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.time.OffsetDateTime;
import java.util.Arrays;
import java.util.UUID;

public record OrderId(String value) implements Serializable {

  public static OrderId randomOrderId(
      @NotNull final Region region, @NotNull final Environment environment) {
    return generateOrderId(region, environment, OffsetDateTime.now(), randomSalt());
  }

  public static OrderId generateOrderId(
      @NotNull final Region region,
      @NotNull final Environment environment,
      @Null final OffsetDateTime offsetDateTime,
      @Null final String salt) {
    final byte[] bytes =
        (region.name() + environment.name() + offsetDateTime + salt)
            .getBytes(StandardCharsets.UTF_8);
    final String uuidWithoutHyphen = UUID.nameUUIDFromBytes(bytes).toString().replace("-", "");

    String identifier = "-" + region.name() + "-" + environment.name() + "-";
    if (environment == Environment.PROD) {
      identifier = "-" + region.name() + "-";
    }

    final int orderIdLength = 32;
    final int length = (orderIdLength - identifier.length()) / 2;
    String orderId =
        uuidWithoutHyphen.substring(0, length)
            + identifier
            + uuidWithoutHyphen.substring(16);
    orderId = orderId.substring(0, orderIdLength);

    return new OrderId(orderId);
  }

  @SneakyThrows
  private static String randomSalt() {
    final byte[] array = new byte[4];
    SecureRandom.getInstanceStrong().nextBytes(array);
    return Arrays.toString(array);
  }
}
