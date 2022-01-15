package science.monke.spring.config;

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@ConstructorBinding
@ConfigurationProperties(prefix = "custom")
data class CustomProperties(
    val region: Region,
    val environment: Environment
)
