package science.monke.util.exception

import org.springframework.http.HttpStatus
import science.monke.util.Error

class NotFoundException : HttpErrorException {

    constructor(error: Error) : super(error, HttpStatus.NOT_FOUND)

    constructor(error: Error, cause: Throwable) : super(error, HttpStatus.NOT_FOUND, cause)
}