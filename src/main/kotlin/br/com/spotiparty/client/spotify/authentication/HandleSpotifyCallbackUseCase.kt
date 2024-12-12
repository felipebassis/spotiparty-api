package br.com.spotiparty.client.spotify.authentication

import br.com.spotiparty.establishment.player.spotify.SpotifyAppDTO
import br.com.spotiparty.establishment.player.spotify.SpotifyCredentialUseCase
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import java.util.*

@Service
class HandleSpotifyCallbackUseCase(
    private val spotifyAuthenticationClient: SpotifyAuthenticationClient,
    private val spotifyCredentialUseCase: SpotifyCredentialUseCase,
    @Value("\${spotify.redirect-uri}") private val redirectUri: String
) {
    fun exchangeAuthorizationCode(
        code: String,
        userId: UUID,
        establishmentId: UUID,
        spotifyAppDTO: SpotifyAppDTO
    ) {

        val credentials = spotifyAuthenticationClient.authenticate(
            "Basic ${
                Base64.getEncoder()
                    .encodeToString("${spotifyAppDTO.clientId}:${spotifyAppDTO.clientSecret}".toByteArray())
            }",
            SpotifyAuthenticationDTO(
                redirectUri = redirectUri,
                authorizationCode = code
            ).toFormData()
        )

        spotifyCredentialUseCase.addCredential(establishmentId, spotifyAppDTO, credentials)
    }
}