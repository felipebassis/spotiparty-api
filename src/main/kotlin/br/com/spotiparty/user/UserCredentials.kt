package br.com.spotiparty.user

import br.com.spotiparty.user.role.Authority
import org.springframework.security.core.userdetails.UserDetails

interface UserCredentials : UserDetails {
    override fun getAuthorities(): MutableCollection<Authority>

    override fun getPassword(): String

    override fun getUsername(): String

    override fun isAccountNonExpired(): Boolean {
        return true
    }

    override fun isAccountNonLocked(): Boolean {
        return true
    }

    override fun isCredentialsNonExpired(): Boolean {
        return true
    }

    override fun isEnabled(): Boolean {
        return true
    }
}