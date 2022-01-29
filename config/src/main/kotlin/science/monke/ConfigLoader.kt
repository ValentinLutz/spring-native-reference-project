package science.monke;

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory
import com.fasterxml.jackson.module.kotlin.readValue
import science.monke.enitity.Config
import java.io.InputStream

class ConfigLoader {

    companion object {
        fun load(): Config {
            val region = System.getProperty("region")
            val environment = System.getProperty("environment")
            return load(region, environment)
        }

        fun load(region: String, environment: String): Config {
            val configName = "$region-$environment.yaml"
            val resourceAsStream: InputStream = this::class.java.classLoader.getResourceAsStream(configName)
                ?: throw IllegalArgumentException("Config: $configName not found")
            val objectMapper = ObjectMapper(YAMLFactory())
            return objectMapper.readValue(resourceAsStream)
        }
    }


}
