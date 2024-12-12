package br.com.spotiparty.login

import jakarta.validation.Valid
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("v1/signin")
internal class SignInController(
    private val userSignOnUseCase: UserSignOnUseCase
) {

    @PostMapping
    fun signIn(@RequestBody @Valid signInDTO: SignInDTO): SignInResponseDTO {
        return userSignOnUseCase.login(signInDTO)
    }
}