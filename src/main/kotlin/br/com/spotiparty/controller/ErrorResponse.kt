package br.com.spotiparty.controller

data class ErrorResponse(
    val errorType: ErrorType,
    val message: String
)
