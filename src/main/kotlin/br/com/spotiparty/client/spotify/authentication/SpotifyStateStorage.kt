package br.com.spotiparty.client.spotify.authentication

import br.com.spotiparty.establishment.player.spotify.SpotifyAppDTO
import com.github.benmanes.caffeine.cache.Caffeine
import java.time.Duration
import java.util.*

internal object SpotifyStateStorage {
    private val CACHE = Caffeine.newBuilder()
        .expireAfterWrite(Duration.ofMinutes(5))
        .build<String, SpotifyState>()

    class SpotifyState(
        val userId: UUID,
        val establishmentId: UUID,
        val spotifyAppDTO: SpotifyAppDTO
    )

    fun put(userId: UUID, establishmentId: UUID, spotifyAppDTO: SpotifyAppDTO): String {
        val key = UUID.randomUUID()
        CACHE.put(key.toString(), SpotifyState(userId, establishmentId, spotifyAppDTO))
        return key.toString()
    }

    fun get(key: String): SpotifyState? {
        return CACHE.getIfPresent(key);
    }
}


