package br.com.spotiparty.client.spotify.authentication

data class SpotifyAuthenticationDTO(
    val grantType: String = "authorization_code",

    val redirectUri: String,

    val authorizationCode: String
) {
    fun toFormData(): Map<String, Any> {
        return mapOf(
            Pair("grant_type", grantType),
            Pair("redirect_uri", redirectUri),
            Pair("code", authorizationCode)
        )
    }
}
