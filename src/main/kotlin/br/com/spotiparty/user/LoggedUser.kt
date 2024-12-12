package br.com.spotiparty.user

import java.util.*

object LoggedUser {
    val SEE_DELETED: ThreadLocal<Boolean> = ThreadLocal<Boolean>()
    val USER_ID: ThreadLocal<UUID> = ThreadLocal<UUID>()
}