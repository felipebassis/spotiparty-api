package br.com.spotiparty.entity

import br.com.spotiparty.cipher.aes.AESCipheredField
import jakarta.persistence.AttributeConverter
import jakarta.persistence.Column
import jakarta.persistence.Convert
import jakarta.persistence.Embeddable
import java.time.OffsetDateTime

@Embeddable
class OAuth2Token(
    @Column(name = "access_token")
    @Convert(converter = OAuth2TokenConverter::class)
    var accessToken: CipheredField,

    @Column(name = "refresh_token")
    @Convert(converter = OAuth2TokenConverter::class)
    var refreshToken: CipheredField,

    @Column(name = "expires_at")
    var expiresAt: OffsetDateTime
) {
    private class OAuth2TokenConverter : AttributeConverter<CipheredField, ByteArray> {
        override fun convertToDatabaseColumn(attribute: CipheredField): ByteArray = attribute.getBytes()

        override fun convertToEntityAttribute(dbData: ByteArray): CipheredField = AESCipheredField(dbData)
    }
}
