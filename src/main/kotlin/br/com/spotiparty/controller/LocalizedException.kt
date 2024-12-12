package br.com.spotiparty.controller

import java.text.MessageFormat

abstract class LocalizedException(
    message: String? = null,
    cause: Throwable? = null,
    private val localizedMessage: MessageFormat,
    private vararg val arguments: Any
) : RuntimeException(message, cause) {

    override fun getLocalizedMessage(): String {
        return localizedMessage.format(arguments)
    }
}