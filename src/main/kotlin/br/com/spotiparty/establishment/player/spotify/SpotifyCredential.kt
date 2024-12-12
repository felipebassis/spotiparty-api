package br.com.spotiparty.establishment.player.spotify

import br.com.spotiparty.entity.OAuth2Credential
import br.com.spotiparty.entity.OAuth2Token
import br.com.spotiparty.entity.tenant.TenantEntity
import br.com.spotiparty.establishment.Establishment
import br.com.spotiparty.establishment.player.AuthenticationProtocol
import jakarta.persistence.Column
import jakarta.persistence.Embedded
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import java.util.*

@Entity
@Table(name = "spotify_credential")
class SpotifyCredential(
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    val id: UUID? = null,

    @Column(name = "account_name")
    var accountName: String,

    @Column(name = "default")
    var default: Boolean = false,

    @Enumerated(EnumType.STRING)
    @Column(name = "authentication_protocol")
    var authenticationProtocol: AuthenticationProtocol = AuthenticationProtocol.OAUTH2,

    @Embedded
    var oauth2Credentials: OAuth2Credential,

    @Embedded
    var oauth2Token: OAuth2Token,

    @ManyToOne
    @JoinColumn(name = "establishment_id")
    override var establishment: Establishment,
) : TenantEntity(establishment)