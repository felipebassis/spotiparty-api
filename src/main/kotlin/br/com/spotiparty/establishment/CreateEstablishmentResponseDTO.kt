package br.com.spotiparty.establishment

import java.math.BigDecimal
import java.util.*

data class CreateEstablishmentResponseDTO(
    val id: UUID,
    val name: String,
    val latitude: BigDecimal,
    val longitude: BigDecimal
)
