package br.com.spotiparty.establishment.player.spotify

import br.com.spotiparty.establishment.player.AuthenticationProtocol
import jakarta.validation.constraints.NotEmpty
import org.jetbrains.annotations.NotNull

data class SpotifyAppDTO(

    @NotNull
    @NotEmpty
    val accountName: String,

    val default: Boolean = false,

    val authenticationProtocol: AuthenticationProtocol = AuthenticationProtocol.OAUTH2,

    @NotNull
    @NotEmpty
    val clientId: String,

    @NotNull
    @NotEmpty
    val clientSecret: String,
)
