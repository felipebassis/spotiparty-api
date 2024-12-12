package br.com.spotiparty.entity

import jakarta.persistence.Column
import jakarta.persistence.Embeddable
import java.math.BigDecimal

@Embeddable
class Geolocation(
    @Column(name = "latitude")
    val latitude: BigDecimal,

    @Column(name = "longitude")
    val longitude: BigDecimal
)