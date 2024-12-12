package br.com.spotiparty.cipher.aes

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "aes")
data class AESCipherProperties(
    val password: String,
    val salt: String,
    val keySize: String,
    val iterations: String,
    val iv: ByteArray = byteArrayOf(-61, 125, -89, 88, -78, -15, 69, -12, 87, 37, 101, -54, -68, 80, 55, -4)
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as AESCipherProperties

        if (password != other.password) return false
        if (salt != other.salt) return false
        if (keySize != other.keySize) return false
        if (iterations != other.iterations) return false
        if (!iv.contentEquals(other.iv)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = password.hashCode()
        result = 31 * result + salt.hashCode()
        result = 31 * result + keySize.hashCode()
        result = 31 * result + iterations.hashCode()
        result = 31 * result + iv.contentHashCode()
        return result
    }
}