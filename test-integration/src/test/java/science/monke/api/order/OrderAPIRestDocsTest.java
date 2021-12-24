package science.monke.api.order;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.restdocs.operation.preprocess.Preprocessors;
import org.springframework.restdocs.restassured3.RestAssuredRestDocumentation;
import science.monke.ConfigLoader;
import science.monke.enitity.Config;

import java.io.IOException;

@ExtendWith({RestDocumentationExtension.class})
class OrderAPIRestDocsTest {

  private RequestSpecification requestSpecification;

  @BeforeAll
  static void beforeAll() throws IOException {
    RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();

    final Config config = ConfigLoader.load();
    RestAssured.baseURI = config.getBaseUri();
    RestAssured.basePath = config.getBasePath();
  }

  @BeforeEach
  void setUp(final RestDocumentationContextProvider restDocumentation) {
    requestSpecification =
        new RequestSpecBuilder()
            .addFilter(
                RestAssuredRestDocumentation.documentationConfiguration(restDocumentation)
                    .operationPreprocessors()
                    .withRequestDefaults(Preprocessors.prettyPrint())
                    .withResponseDefaults(Preprocessors.prettyPrint()))
            .build();
  }

  @Test
  void getOrders() {
    RestAssured.given(requestSpecification)
        .filter(RestAssuredRestDocumentation.document("getOrders"))
        .when()
        .get("/orders")
        .then()
        .statusCode(200);
  }

  @Test
  void postOrder() {
    RestAssured.given(requestSpecification)
        .filter(RestAssuredRestDocumentation.document("postOrder"))
        .when()
        .post("/orders")
        .then()
        .statusCode(201);
  }

  @Test
  void getOrder() {
    RestAssured.given(requestSpecification)
        .filter(RestAssuredRestDocumentation.document("getOrder"))
        .when()
        .get("/orders/1234")
        .then()
        .statusCode(200);
  }
}
