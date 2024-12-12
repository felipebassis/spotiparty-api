package br.com.spotiparty.user.role

import org.springframework.data.repository.Repository
import java.util.*

interface RoleRepository : Repository<Role, UUID> {

    fun findAll(): MutableList<Role>
}