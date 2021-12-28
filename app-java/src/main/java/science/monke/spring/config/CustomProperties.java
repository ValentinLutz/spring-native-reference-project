package science.monke.spring.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import science.monke.internal.order.entity.Region;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "custom")
public class CustomProperties {
  private Region region;
}
