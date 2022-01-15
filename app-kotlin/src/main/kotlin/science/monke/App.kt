package science.monke

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication
import science.monke.spring.config.CustomProperties


@SpringBootApplication
@EnableConfigurationProperties(CustomProperties::class)
class App

fun main(args: Array<String>) {
    runApplication<App>(*args)
}

