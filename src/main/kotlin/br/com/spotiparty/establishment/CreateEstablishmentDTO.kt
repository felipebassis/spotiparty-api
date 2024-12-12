package br.com.spotiparty.establishment

import jakarta.validation.constraints.Max
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull
import java.math.BigDecimal

data class CreateEstablishmentDTO(

    @NotNull
    @NotEmpty
    val name: String,

    @Max(value = 90L)
    @Min(value = -90L)
    val latitude: BigDecimal,

    @Max(value = 180L)
    @Min(value = -180L)
    val longitude: BigDecimal
)
