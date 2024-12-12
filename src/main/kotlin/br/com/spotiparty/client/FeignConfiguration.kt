package br.com.spotiparty.client

import feign.codec.Encoder
import feign.form.FormEncoder
import org.springframework.beans.factory.ObjectFactory
import org.springframework.boot.autoconfigure.http.HttpMessageConverters
import org.springframework.cloud.openfeign.support.SpringEncoder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary

@Configuration
class FeignConfiguration(
    private val messageConverters: ObjectFactory<HttpMessageConverters>
) {
    @Bean
    @Primary
    fun feignFormEncoder(): Encoder = FormEncoder(SpringEncoder(messageConverters))

}