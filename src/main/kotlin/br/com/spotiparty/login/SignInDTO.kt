package br.com.spotiparty.login

import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull

data class SignInDTO(

    @NotNull
    @NotEmpty
    val username: String,

    @NotNull
    @NotEmpty
    val password: String
)