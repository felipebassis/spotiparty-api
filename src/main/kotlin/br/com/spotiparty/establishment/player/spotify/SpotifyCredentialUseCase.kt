package br.com.spotiparty.establishment.player.spotify

import br.com.spotiparty.cipher.Algorithm
import br.com.spotiparty.cipher.CipherFactory
import br.com.spotiparty.cipher.CryptographicCipher
import br.com.spotiparty.client.spotify.authentication.SpotifyAuthenticationResponseDTO
import br.com.spotiparty.client.spotify.authentication.SpotifyScope
import br.com.spotiparty.client.spotify.authentication.SpotifyStateStorage
import br.com.spotiparty.entity.OAuth2Credential
import br.com.spotiparty.entity.OAuth2Token
import br.com.spotiparty.establishment.EstablishmentRepository
import br.com.spotiparty.establishment.exception.EstablishmentNotFoundException
import br.com.spotiparty.user.LoggedUser
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.util.UriComponentsBuilder
import java.time.OffsetDateTime
import java.util.*

@Service
class SpotifyCredentialUseCase(
    @Value("\${spotify.authorization-api}")
    private val spotifyAuthorizationApiUrl: String,
    @Value("\${spotify.redirect-uri}") private val redirectUri: String,
    private val spotifyCredentialRepository: SpotifyCredentialRepository,
    private val establishmentRepository: EstablishmentRepository,
    cipherFactory: CipherFactory,
) {

    private val cryptographicCipher: CryptographicCipher = cipherFactory.getCipherInstance(Algorithm.AES)

    fun getSpotifyApiAuthorizationUrl(
        establishmentId: UUID,
        credentials: SpotifyAppDTO
    ): SpotifyApiAuthorizationUrlResponseDTO {
        val state = SpotifyStateStorage.put(
            userId = LoggedUser.USER_ID.get(),
            establishmentId = establishmentId,
            spotifyAppDTO = credentials
        )
        return SpotifyApiAuthorizationUrlResponseDTO(
            UriComponentsBuilder.fromHttpUrl("$spotifyAuthorizationApiUrl/authorize")
                .queryParam("response_type", "code")
                .queryParam("client_id", credentials.clientId)
                .queryParam("scope", SpotifyScope.ALL_SCOPES.joinToString(separator = " ") { it })
                .queryParam("redirect_uri", redirectUri)
                .queryParam("state", state)
                .toUriString()
        )
    }

    @Transactional
    fun addCredential(
        establishmentId: UUID,
        credentials: SpotifyAppDTO,
        tokenResponse: SpotifyAuthenticationResponseDTO
    ) {
        val establishment = establishmentRepository.findById(establishmentId)
            .orElseThrow {
                EstablishmentNotFoundException("Establishment $establishmentId not found.")
            }


        val credential = spotifyCredentialRepository.findByEstablishmentAndOauth2Credentials(
            establishment, OAuth2Credential(
                clientId = cryptographicCipher.encrypt(credentials.clientId),
                clientSecret = cryptographicCipher.encrypt(credentials.clientSecret)
            )
        )?.apply {
            this.oauth2Token = OAuth2Token(
                accessToken = cryptographicCipher.encrypt(tokenResponse.accessToken),
                refreshToken = cryptographicCipher.encrypt(tokenResponse.refreshToken),
                expiresAt = OffsetDateTime.now()
                    .minusMinutes(1)
                    .plusSeconds(tokenResponse.expiresIn.toLong())
            )
        } ?: credentials.let {
            SpotifyCredential(
                accountName = it.accountName,
                establishment = establishment,
                default = it.default,
                oauth2Token = OAuth2Token(
                    accessToken = cryptographicCipher.encrypt(tokenResponse.accessToken),
                    refreshToken = cryptographicCipher.encrypt(tokenResponse.refreshToken),
                    expiresAt = OffsetDateTime.now()
                        .minusMinutes(1)
                        .plusSeconds(tokenResponse.expiresIn.toLong())
                ),
                oauth2Credentials = OAuth2Credential(
                    clientId = cryptographicCipher.encrypt(it.clientId),
                    clientSecret = cryptographicCipher.encrypt(it.clientSecret)
                )
            )
        }

        val previousDefaultCredential = spotifyCredentialRepository.findByEstablishmentAndDefaultTrue(establishment)
        if (previousDefaultCredential != null &&
            credential.id != previousDefaultCredential.id &&
            credentials.default
        ) {
            previousDefaultCredential.default = false
            spotifyCredentialRepository.save(previousDefaultCredential)
        }
        spotifyCredentialRepository.save(credential)
    }
}
