package br.com.spotiparty.cipher.aes

import br.com.spotiparty.cipher.Algorithm
import br.com.spotiparty.cipher.CryptographicCipher
import br.com.spotiparty.cipher.exception.WrongAlgorithmException
import br.com.spotiparty.entity.CipheredField
import org.springframework.stereotype.Component
import javax.crypto.Cipher
import javax.crypto.SecretKey
import javax.crypto.SecretKeyFactory
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.PBEKeySpec
import javax.crypto.spec.SecretKeySpec

@Component
internal class AESCryptographicCipher(
    properties: AESCipherProperties
) : CryptographicCipher {

    private val secretKey: SecretKey = let {
        val keyFactory = SecretKeyFactory.getInstance(SECRET_KEY_ALGORITHM)
        val keySpec = PBEKeySpec(
            properties.password.toCharArray(),
            properties.salt.toByteArray(),
            properties.iterations.toInt(),
            properties.keySize.toInt()
        )
        SecretKeySpec(keyFactory.generateSecret(keySpec).encoded, "AES")
    }

    private val initializationVector: ByteArray = properties.iv

    override fun getCipherAlgorithm(): Algorithm = Algorithm.AES

    override fun encrypt(value: String): AESCipheredField {
        val cipher = Cipher.getInstance(getCipherAlgorithm().algorithm)
        val ivParameterSpec = IvParameterSpec(initializationVector)
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivParameterSpec)
        val encodedValue = cipher.doFinal(value.toByteArray())
        return AESCipheredField(encodedValue)
    }

    override fun decrypt(value: CipheredField): String {
        if (value.cipherAlgorithm() != getCipherAlgorithm()) throw WrongAlgorithmException("Ciphered field does not use AES encryption.")
        val cipher = Cipher.getInstance(getCipherAlgorithm().algorithm)
        val ivParameterSpec = IvParameterSpec(initializationVector)
        cipher.init(Cipher.DECRYPT_MODE, secretKey, ivParameterSpec)
        return String(cipher.doFinal(value.getBytes()))

    }

    companion object {
        const val SECRET_KEY_ALGORITHM: String = "PBKDF2WithHmacSHA256"
    }
}