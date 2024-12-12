package br.com.spotiparty

import br.com.spotiparty.authentication.JwtProperties
import br.com.spotiparty.cipher.aes.AESCipherProperties
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication
import org.springframework.cloud.openfeign.EnableFeignClients
import org.springframework.context.annotation.EnableAspectJAutoProxy

@SpringBootApplication
@EnableAspectJAutoProxy
@EnableFeignClients
@EnableConfigurationProperties(JwtProperties::class, AESCipherProperties::class)
class SpotiPartyApplication

fun main(args: Array<String>) {
    runApplication<SpotiPartyApplication>(*args)
}
