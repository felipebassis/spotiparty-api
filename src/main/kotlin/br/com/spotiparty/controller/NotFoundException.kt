package br.com.spotiparty.controller

abstract class NotFoundException(
    message: String,
    cause: Throwable? = null
) : RuntimeException(message, cause)