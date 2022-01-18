package science.monke.api;

import io.restassured.module.kotlin.extensions.Given
import io.restassured.module.kotlin.extensions.Then
import io.restassured.module.kotlin.extensions.When
import org.junit.jupiter.api.Test
import science.monke.AbstractSmokeTest

class SwaggerSmokeTest : AbstractSmokeTest() {
    @Test
    fun swagger() {
        Given {
            with()
        } When {
            get("/swagger")
        } Then {
            statusCode(200)
        }
    }
}
