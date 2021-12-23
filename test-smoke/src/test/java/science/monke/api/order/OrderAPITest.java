package science.monke.api.order;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class OrderAPITest {

  @BeforeAll
  static void beforeAll() {
    RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    RestAssured.baseURI = "http://localhost:8080";
    RestAssured.basePath = "/api";
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
