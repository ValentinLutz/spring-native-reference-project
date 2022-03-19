package science.monke.spring.error

import org.springframework.core.Ordered
import org.springframework.core.annotation.Order
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import science.monke.util.exception.HttpErrorException
import javax.servlet.http.HttpServletRequest

@ControllerAdvice
class ApiResponseEntityExceptionHandler : ResponseEntityExceptionHandler() {

    @ExceptionHandler(HttpErrorException::class)
    fun handleCustomRuntimeException(
        httpErrorException: HttpErrorException,
        request: HttpServletRequest
    ): ResponseEntity<ErrorResponse> {
        val errorResponse = ErrorResponse(
            path = request.requestURI,
            status = httpErrorException.status,
            error = httpErrorException.error,
            message = httpErrorException.message
        )
        logger.info(errorResponse.message, httpErrorException)
        return ResponseEntity<ErrorResponse>(errorResponse, errorResponse.status)
    }

    @Order(Ordered.LOWEST_PRECEDENCE)
    @ExceptionHandler(Exception::class)
    fun handleException(exception: Exception, request: HttpServletRequest): ResponseEntity<ErrorResponse> {
        val errorResponse = ErrorResponse(
            path = request.requestURI,
            status = HttpStatus.INTERNAL_SERVER_ERROR,
            error = 9009,
            message = "Panic, it's over 9000"
        )
        logger.error(errorResponse.message, exception)
        return ResponseEntity<ErrorResponse>(errorResponse, errorResponse.status)
    }
}