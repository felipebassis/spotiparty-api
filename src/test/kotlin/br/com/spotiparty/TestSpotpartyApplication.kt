package br.com.spotiparty

import org.springframework.boot.fromApplication
import org.springframework.boot.with


fun main(args: Array<String>) {
    fromApplication<SpotiPartyApplication>().with(TestcontainersConfiguration::class).run(*args)
}
