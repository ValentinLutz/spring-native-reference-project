package science.monke

import io.restassured.RestAssured
import io.restassured.builder.RequestSpecBuilder
import io.restassured.http.ContentType
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.TestInstance

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
abstract class AbstractIntegrationTest {


    @BeforeAll
    fun beforeAll() {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails()
        //        val config: Config = ConfigLoader.load()
//        RestAssured.baseURI = config.baseUri
//        RestAssured.basePath = config.basePath

        RestAssured.baseURI = "http://localhost:8080"
        RestAssured.basePath = "/api"
        RestAssured.requestSpecification = RequestSpecBuilder().build()
        RestAssured.requestSpecification.contentType(ContentType.JSON)

    }
}