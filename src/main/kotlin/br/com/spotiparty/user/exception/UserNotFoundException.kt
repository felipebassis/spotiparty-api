package br.com.spotiparty.user.exception

import br.com.spotiparty.controller.NotFoundException

class UserNotFoundException(
    message: String,
    cause: Throwable? = null
) : NotFoundException(message, cause)