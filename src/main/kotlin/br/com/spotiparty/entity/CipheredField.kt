package br.com.spotiparty.entity

import br.com.spotiparty.cipher.Algorithm

interface CipheredField {
    fun cipherAlgorithm(): Algorithm
    fun getBytes(): ByteArray
}
