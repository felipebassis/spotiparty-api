package br.com.spotiparty.client.spotify.authentication

import br.com.spotiparty.client.OAuth2Scope

enum class SpotifyScope(
    private val scopeName: String,
) : OAuth2Scope {

    READ_PLAYBACK_STATE("user-read-playback-state"),
    MODIFY_PLAYBACK_STATE("user-modify-playback-state"),
    READ_CURRENTLY_PLAYING("user-read-currently-playing"),

    PLAYLIST_READ_PRIVATE("playlist-read-private"), ;

    override fun getName() = scopeName

    companion object {
        val ALL_SCOPES: List<String> = entries.map(SpotifyScope::scopeName)
    }
}