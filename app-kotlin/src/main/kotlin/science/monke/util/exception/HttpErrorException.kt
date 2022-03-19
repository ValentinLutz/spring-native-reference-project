package science.monke.util.exception

import org.springframework.http.HttpStatus
import science.monke.util.Error

open class HttpErrorException : RuntimeException {

    val status: HttpStatus
    val error: Int

    constructor(error: Error, status: HttpStatus) : super(error.message) {
        this.error = error.id
        this.status = status
    }

    constructor(error: Error, status: HttpStatus, cause: Throwable) : super(error.message, cause) {
        this.error = error.id
        this.status = status
    }
}