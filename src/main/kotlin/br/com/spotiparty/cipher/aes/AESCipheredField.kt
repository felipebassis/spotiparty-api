package br.com.spotiparty.cipher.aes

import br.com.spotiparty.cipher.Algorithm
import br.com.spotiparty.entity.CipheredField

class AESCipheredField(
    private val value: ByteArray
) : CipheredField {

    override fun cipherAlgorithm(): Algorithm = Algorithm.AES

    override fun getBytes() = value
}
