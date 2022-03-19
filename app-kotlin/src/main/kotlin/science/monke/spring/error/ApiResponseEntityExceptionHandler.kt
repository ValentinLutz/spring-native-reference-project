package science.monke.spring.error

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.core.Ordered
import org.springframework.core.annotation.Order
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.server.reactive.ServerHttpRequest
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import science.monke.util.exception.HttpErrorException

@RestControllerAdvice
class ApiResponseEntityExceptionHandler {
    
    val logger: Logger = LoggerFactory.getLogger(ApiResponseEntityExceptionHandler::class.java)

    @ExceptionHandler(HttpErrorException::class)
    fun handleCustomRuntimeException(
        httpErrorException: HttpErrorException,
        request: ServerHttpRequest
    ): ResponseEntity<ErrorResponse> {
        val errorResponse = ErrorResponse(
            path = request.path.value(),
            status = httpErrorException.status,
            error = httpErrorException.error,
            message = httpErrorException.message
        )
//        logger.info(errorResponse.message, httpErrorException)
        return ResponseEntity<ErrorResponse>(errorResponse, errorResponse.status)
    }

    @Order(Ordered.LOWEST_PRECEDENCE)
    @ExceptionHandler(Exception::class)
    fun handleException(exception: Exception, request: ServerHttpRequest): ResponseEntity<ErrorResponse> {
        val errorResponse = ErrorResponse(
            path = request.path.value(),
            status = HttpStatus.INTERNAL_SERVER_ERROR,
            error = 9009,
            message = "Panic, it's over 9000"
        )
        logger.error(errorResponse.message, exception)
        return ResponseEntity<ErrorResponse>(errorResponse, errorResponse.status)
    }
}