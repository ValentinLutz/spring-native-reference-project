package science.monke.api.actuator;

import io.restassured.module.kotlin.extensions.Given
import io.restassured.module.kotlin.extensions.Then
import io.restassured.module.kotlin.extensions.When
import org.junit.jupiter.api.Test
import science.monke.AbstractSmokeTest

class HealthSmokeTest : AbstractSmokeTest() {

    @Test
    fun health() {
        Given {
            with()
        } When {
            get("/actuator/health")
        } Then {
            statusCode(200)
        }
    }

    @Test
    fun healthDatabase() {
        Given {
            with()
        } When {
            get("/actuator/health/r2dbc")
        } Then {
            statusCode(200)
        }
    }
}
