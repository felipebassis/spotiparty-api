package br.com.spotiparty.cipher

import br.com.spotiparty.entity.CipheredField

interface CryptographicCipher {
    fun getCipherAlgorithm(): Algorithm
    fun encrypt(value: String): CipheredField
    fun decrypt(value: CipheredField): String
}