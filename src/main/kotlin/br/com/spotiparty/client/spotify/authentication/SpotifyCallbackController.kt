package br.com.spotiparty.client.spotify.authentication

import br.com.spotiparty.client.spotify.authentication.exception.CallbackErrorException
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("v1/spotify/callback")
class SpotifyCallbackController(
    private val handleSpotifyCallbackUseCase: HandleSpotifyCallbackUseCase
) {

    @GetMapping
    fun callback(
        @RequestParam("code") code: String?,
        @RequestParam("error") error: String?,
        @RequestParam("state") state: String
    ) {
        if (error != null) {
            throw CallbackErrorException(error)
        }
        val previousState = SpotifyStateStorage.get(state)
            ?: throw IllegalStateException("This request was not made by this application and should not be handled")

        handleSpotifyCallbackUseCase.exchangeAuthorizationCode(
            code!!,
            previousState.userId,
            previousState.establishmentId,
            previousState.spotifyAppDTO
        )
    }
}
