package computerdatabase;

// required for Gatling core structure DSL

import io.gatling.javaapi.core.ScenarioBuilder;
import io.gatling.javaapi.core.Simulation;
import io.gatling.javaapi.http.HttpProtocolBuilder;

import static io.gatling.javaapi.core.CoreDsl.constantUsersPerSec;
import static io.gatling.javaapi.core.CoreDsl.scenario;
import static io.gatling.javaapi.http.HttpDsl.http;

public class BasicSimulation extends Simulation {

  HttpProtocolBuilder httpProtocol =
      http.baseUrl("http://localhost:8080/api") // Here is the root for all relative URLs
          .acceptHeader(
              "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8") // Here are the
          // common headers
          .acceptEncodingHeader("gzip, deflate")
          .acceptLanguageHeader("en-US,en;q=0.5")
          .userAgentHeader(
              "Mozilla/5.0 (Macintosh; Intel Mac OS X 10.8; rv:16.0) Gecko/20100101 Firefox/16.0");

  ScenarioBuilder scn =
      scenario("Order Scenario")
          .exec(http("GET Orders").get("/orders"))
          .pause(5)
          .exec(http("POST Order").post("/orders"))
          .pause(5)
          .exec(http("GET Order").get("/orders/1234"));

  {
    setUp(scn.injectOpen(constantUsersPerSec(10).during(60)).protocols(httpProtocol));
  }
}
