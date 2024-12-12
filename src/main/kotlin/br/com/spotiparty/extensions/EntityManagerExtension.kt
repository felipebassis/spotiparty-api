package br.com.spotiparty.extensions

import jakarta.persistence.EntityManager
import org.hibernate.Session

fun EntityManager.getSession(): Session {
    return this.unwrap(Session::class.java)
}