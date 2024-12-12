package br.com.spotiparty.extensions

import java.time.OffsetDateTime
import java.util.*

fun OffsetDateTime.toDate(): Date {
    return Date.from(this.toInstant())
}
