package br.com.spotiparty.cipher

import org.springframework.stereotype.Component

@Component
class CipherFactory(
    private val cryptographicCiphers: List<CryptographicCipher>
) {
    fun getCipherInstance(algorithm: Algorithm): CryptographicCipher = cryptographicCiphers.first {
        it.getCipherAlgorithm() == algorithm
    }
}