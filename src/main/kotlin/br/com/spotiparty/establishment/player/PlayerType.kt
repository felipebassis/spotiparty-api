package br.com.spotiparty.establishment.player

enum class PlayerType(
    val allowedAuthenticationProtocol: List<AuthenticationProtocol>
) {
    SPOTIFY(listOf(AuthenticationProtocol.OAUTH2));
}
