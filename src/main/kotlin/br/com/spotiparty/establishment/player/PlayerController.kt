package br.com.spotiparty.establishment.player

import br.com.spotiparty.establishment.player.spotify.SpotifyAppDTO
import br.com.spotiparty.establishment.player.spotify.SpotifyCredentialUseCase
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
@RequestMapping("v1/establishment/{id}")
internal class PlayerController(
    private val spotifyCredentialUseCase: SpotifyCredentialUseCase
) {

    @PostMapping("spotify")
    @ResponseStatus(HttpStatus.CREATED)
    fun getSpotifyApiAuthorizationUrl(
        @PathVariable("id") establishmentId: UUID,
        @Valid @RequestBody credentials: SpotifyAppDTO
    ) = spotifyCredentialUseCase.getSpotifyApiAuthorizationUrl(establishmentId, credentials)
}