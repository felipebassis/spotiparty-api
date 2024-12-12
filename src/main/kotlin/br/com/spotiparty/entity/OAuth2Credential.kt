package br.com.spotiparty.entity

import br.com.spotiparty.cipher.aes.AESCipheredField
import jakarta.persistence.AttributeConverter
import jakarta.persistence.Column
import jakarta.persistence.Convert
import jakarta.persistence.Embeddable

@Embeddable
class OAuth2Credential(

    @Column(name = "client_id")
    @Convert(converter = OAuth2CredentialsConverter::class)
    var clientId: CipheredField,

    @Column(name = "client_secret")
    @Convert(converter = OAuth2CredentialsConverter::class)
    var clientSecret: CipheredField
) {
    private class OAuth2CredentialsConverter : AttributeConverter<CipheredField, ByteArray> {
        override fun convertToDatabaseColumn(attribute: CipheredField): ByteArray = attribute.getBytes()

        override fun convertToEntityAttribute(dbData: ByteArray): CipheredField = AESCipheredField(dbData)
    }
}
