package science.monke.api.order;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import science.monke.ConfigLoader;
import science.monke.enitity.Config;

import java.io.IOException;

class OrderAPITest {

  @BeforeAll
  static void beforeAll() throws IOException {
    RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();

    final Config config = ConfigLoader.load();
    RestAssured.baseURI = config.getBaseUri();
    RestAssured.basePath = config.getBasePath();
  }

  @Test
  void getOrders() {
    RestAssured.given().when().get("/orders").then().statusCode(200);
  }

  @Test
  void postOrder() {
    RestAssured.given().when().post("/orders").then().statusCode(201);
  }

  @Test
  void getOrder() {
    RestAssured.when().get("/orders/1234").then().statusCode(200);
  }
}
