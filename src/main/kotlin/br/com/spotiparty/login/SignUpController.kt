package br.com.spotiparty.login

import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("v1/signup")
internal class SignUpController(
    private val userSignOnUseCase: UserSignOnUseCase
) {
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun signUp(@RequestBody @Valid userDTO: UserDTO) {
        userSignOnUseCase.registerUser(userDTO)
    }
}
