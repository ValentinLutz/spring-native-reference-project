package science.monke.spring.exception

import org.springframework.http.HttpStatus
import science.monke.spring.error.Error

class NotFoundException : HttpErrorException {

    constructor(error: Error) : super(error, HttpStatus.NOT_FOUND)

    constructor(error: Error, cause: Throwable) : super(error, HttpStatus.NOT_FOUND, cause)
}