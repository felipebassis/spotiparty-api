package br.com.spotiparty.client.spotify.queue

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestHeader

@FeignClient(name = "spotifyQueue", url = "\${spotify.api}")
interface SpotifyQueueClient {

    @GetMapping("/v1/me/player/queue")
    fun getQueue(@RequestHeader("Authorization") token: String): Any
}