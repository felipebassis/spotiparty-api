package br.com.spotiparty.establishment

import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("v1/establishment")
internal class EstablishmentController(
    private val establishmentUseCase: EstablishmentUseCase,
) {


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createEstablishment(
        @Valid
        @RequestBody
        createEstablishmentDTO: CreateEstablishmentDTO
    ): CreateEstablishmentResponseDTO = establishmentUseCase.create(createEstablishmentDTO)
}