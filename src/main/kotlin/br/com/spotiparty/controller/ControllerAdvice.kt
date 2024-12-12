package br.com.spotiparty.controller

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class ControllerAdvice {

    @ExceptionHandler(NotFoundException::class)
    fun handleNotFoundException(exception: NotFoundException): ErrorResponse {
        return handleException(ErrorType.ERROR, exception)
    }

    private fun handleException(errorType: ErrorType, exception: Exception): ErrorResponse {
        LOGGER.error("Exception Thrown: {}", exception.message, exception)
        return ErrorResponse(errorType, exception.localizedMessage)
    }

    companion object {
        private val LOGGER: Logger = LoggerFactory.getLogger(ControllerAdvice::class.java)
    }
}
