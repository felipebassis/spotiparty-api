package br.com.spotiparty.login

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull

data class UserDTO(

    @NotNull
    @NotEmpty
    val username: String,

    @NotNull
    @NotEmpty
    //TODO("Implementar annotation de validação de força de senha")
    val password: String,

    @Email
    val email: String?,


    //TODO("Implementar annotation de validação de formato de celular")
    val cellphone: String?
)