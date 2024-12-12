package br.com.spotiparty.queue

import br.com.spotiparty.cipher.Algorithm
import br.com.spotiparty.cipher.CipherFactory
import br.com.spotiparty.cipher.CryptographicCipher
import br.com.spotiparty.client.spotify.queue.SpotifyQueueClient
import br.com.spotiparty.establishment.EstablishmentRepository
import br.com.spotiparty.establishment.player.PlayerType
import br.com.spotiparty.establishment.player.spotify.SpotifyCredentialRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class MusicQueueUseCase(
    private val spotifyQueueClient: SpotifyQueueClient,
    private val establishmentRepository: EstablishmentRepository,
    private val spotifyCredentialRepository: SpotifyCredentialRepository,
    cipherFactory: CipherFactory
) {

    private val cryptographicCipher: CryptographicCipher = cipherFactory.getCipherInstance(Algorithm.AES)

    fun getQueue(establishmentId: UUID, playerType: PlayerType): Any {
        return spotifyQueueClient.getQueue(
            "Bearer ${
                cryptographicCipher.decrypt(
                    spotifyCredentialRepository.findByEstablishmentAndDefaultTrue(
                        establishmentRepository.findById(establishmentId)
                            .orElse(null)
                    )!!
                        .oauth2Token
                        .accessToken
                )
            }"
        )
    }

}
