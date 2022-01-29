package computerdatabase;

import io.gatling.javaapi.core.CoreDsl.rampUsersPerSec
import io.gatling.javaapi.core.CoreDsl.scenario
import io.gatling.javaapi.core.Simulation
import io.gatling.javaapi.http.HttpDsl.http

class BasicSimulation : Simulation() {

    val httpProtocol =
        http.baseUrl("http://localhost:8080/api") // Here is the root for all relative URLs
            .acceptHeader(
                "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8"
            ) // Here are the
            // common headers
            .acceptEncodingHeader("gzip, deflate")
            .acceptLanguageHeader("en-US,en;q=0.5")
            .userAgentHeader(
                "Mozilla/5.0 (Macintosh; Intel Mac OS X 10.8; rv:16.0) Gecko/20100101 Firefox/16.0"
            );

    val scn =
        scenario("Order Scenario")
            .exec(http("GET Orders").get("/orders"))
            .exec(http("GET Order").get("/orders/Y2FvghhpMxG-NONE-DEV-qzefk8Ziv0A"))

    init {
        setUp(
            scn.injectOpen(
                rampUsersPerSec(0.0).to(500.0).during(60)
            ).protocols(httpProtocol)
        )
    }
}
