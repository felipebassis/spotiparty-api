package br.com.spotiparty.authentication

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties("jwt")
data class JwtProperties(
    var secret: String,
    var issuer: String
) {

}
