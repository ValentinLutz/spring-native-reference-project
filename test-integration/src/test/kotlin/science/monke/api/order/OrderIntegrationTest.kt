package science.monke.api.order;

import io.restassured.module.kotlin.extensions.Given
import io.restassured.module.kotlin.extensions.Then
import io.restassured.module.kotlin.extensions.When
import org.junit.jupiter.api.Test
import science.monke.AbstractIntegrationTest

class OrderIntegrationTest : AbstractIntegrationTest() {

    @Test
    fun `get all orders`() {
        Given {
            with()
        } When {
            get("/orders")
        } Then {
            statusCode(200)
        }
    }

    @Test
    fun `create a new order with one order item`() {
        val requestBody = this::class.java
            .getResource("post_orders_singleItem.json")
            ?.readText()

        Given {
            with()
            body(requestBody)
        } When {
            post("/orders")
        } Then {
            statusCode(201)
        }
    }


    @Test
    fun `create a new order with multiple order items`() {
        val requestBody = this::class.java
            .getResource("post_orders_multipleItems.json")
            ?.readText()

        Given {
            with()
            body(requestBody)
        } When {
            post("/orders")
        } Then {
            statusCode(201)
        }
    }

    @Test
    fun `get a single order`() {
        Given {
            with()
        } When {
            get("/orders/Y2FvghhpMxG-NONE-DEV-qzefk8Ziv0A")
        } Then {
            statusCode(200)
        }
    }
}
