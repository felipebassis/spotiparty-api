package br.com.spotiparty.user

import br.com.spotiparty.entity.softdelete.SoftDeleteRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import java.util.*

interface UserRepository : SoftDeleteRepository<User, UUID> {
    fun findByUsername(login: String?): User?

    @Query(
        value = """
        select u from User u 
            where u.username = :login or
            u.cellphone = :login or
            u.email = :login
    """
    )
    fun findByLoginParameters(
        @Param("login") login: String?
    ): User?
}