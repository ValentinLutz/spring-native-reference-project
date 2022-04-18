package science.monke.api.test

import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.*
import science.monke.api.billing.BillingSNSPublisher

@Tag(name = "test")
@RestController
@RequestMapping("/test")
class TestController(
    private val billingSNSPublisher: BillingSNSPublisher
) {

    @PostMapping
    @ApiResponse(
        responseCode = "200"
    )
    fun publishBillingMessages(@RequestParam amount: Int, @RequestBody message: String) {
        return billingSNSPublisher.publishMessages(message, amount)
    }
}
