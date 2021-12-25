package science.monke.api.order;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import science.monke.ConfigLoader;
import science.monke.enitity.Config;

import java.io.IOException;

class SwaggerSmokeTest {

  @BeforeAll
  static void beforeAll() throws IOException {
    RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();

    final Config config = ConfigLoader.load();
    RestAssured.baseURI = config.getBaseUri();
    RestAssured.basePath = config.getBasePath();
  }

  @Test
  void swagger() {
    RestAssured.given().when().get("/swagger").then().statusCode(200);
  }
}
