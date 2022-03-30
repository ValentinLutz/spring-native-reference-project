package science.monke.spring.exception

import org.springframework.http.HttpStatus
import science.monke.spring.error.Error

class BadRequestException : HttpErrorException {

    constructor(error: Error) : super(error, HttpStatus.BAD_REQUEST)

    constructor(error: Error, cause: Throwable) : super(error, HttpStatus.BAD_REQUEST, cause)
}