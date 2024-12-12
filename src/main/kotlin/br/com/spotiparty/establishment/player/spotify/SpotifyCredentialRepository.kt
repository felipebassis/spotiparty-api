package br.com.spotiparty.establishment.player.spotify

import br.com.spotiparty.entity.OAuth2Credential
import br.com.spotiparty.entity.softdelete.SoftDeleteRepository
import br.com.spotiparty.establishment.Establishment
import java.util.*

interface SpotifyCredentialRepository : SoftDeleteRepository<SpotifyCredential, UUID> {

    fun findByEstablishmentAndDefaultTrue(establishment: Establishment): SpotifyCredential?
    fun findByEstablishmentAndOauth2Credentials(
        establishment: Establishment,
        oauth2Credentials: OAuth2Credential
    ): SpotifyCredential?
}