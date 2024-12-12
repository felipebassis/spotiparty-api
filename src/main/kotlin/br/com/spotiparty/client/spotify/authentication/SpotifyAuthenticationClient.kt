package br.com.spotiparty.client.spotify.authentication

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestHeader

@FeignClient(name = "spotifyAuthentication", url = "\${spotify.authorization-api}")
interface SpotifyAuthenticationClient {

    @PostMapping(
        path = ["api/token"],
        consumes = [MediaType.APPLICATION_FORM_URLENCODED_VALUE]
    )
    fun authenticate(
        @RequestHeader("Authorization") authorization: String,
        spotifyAuthenticationDTO: Map<String, Any>
    ): SpotifyAuthenticationResponseDTO
}