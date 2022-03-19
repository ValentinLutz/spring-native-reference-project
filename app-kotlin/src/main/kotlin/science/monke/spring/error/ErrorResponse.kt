package science.monke.spring.error

import org.springframework.http.HttpStatus
import java.time.OffsetDateTime

data class ErrorResponse(
    val timestamp: OffsetDateTime = OffsetDateTime.now(),
    val path: String,
    val status: HttpStatus,
    val error: Int,
    val message: String?
)