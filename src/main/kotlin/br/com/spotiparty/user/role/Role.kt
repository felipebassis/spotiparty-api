package br.com.spotiparty.user.role

import jakarta.persistence.Column
import jakarta.persistence.Convert
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import org.springframework.security.core.GrantedAuthority
import java.util.*

@Entity
@Table(name = "role")
class Role(
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private val id: UUID,

    @Column(name = "name")
    @Convert(converter = AuthorityConverter::class)
    val name: Authority
) : GrantedAuthority {

    override fun getAuthority(): String {
        return name.authority
    }
}
