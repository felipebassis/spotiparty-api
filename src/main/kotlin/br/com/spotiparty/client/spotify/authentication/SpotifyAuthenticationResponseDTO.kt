package br.com.spotiparty.client.spotify.authentication

import com.fasterxml.jackson.annotation.JsonAlias

data class SpotifyAuthenticationResponseDTO(
    @JsonAlias("access_token")
    val accessToken: String,

    @JsonAlias("token_type")
    val tokenType: String,

    @JsonAlias("expires_in")
    val expiresIn: Int,

    @JsonAlias("refresh_token")
    val refreshToken: String
)
