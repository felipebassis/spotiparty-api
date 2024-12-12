package br.com.spotiparty.user

import br.com.spotiparty.entity.softdelete.SoftDeleteEntity
import br.com.spotiparty.user.role.Authority
import br.com.spotiparty.user.role.Role
import jakarta.persistence.CascadeType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.JoinTable
import jakarta.persistence.OneToMany
import jakarta.persistence.Table
import org.hibernate.annotations.SQLDelete
import java.util.*

@Entity
@Table(name = "user")
class User(
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    val id: UUID? = null,

    @get:JvmName("getUsernameJvm")
    @Column(name = "username")
    var username: String,

    @get:JvmName("getPasswordJvm")
    @Column(name = "password")
    var password: String,

    @Column(name = "email")
    var email: String?,

    @Column(name = "cellphone")
    var cellphone: String?,

    @Column(name = "banned")
    var banned: Boolean = false,

    @SQLDelete(
        sql = """
        update user_role set deleted = true where user_id = ?
    """
    )
    @JoinTable(
        name = "user_role",
        joinColumns = [JoinColumn(name = "user_id")],
        inverseJoinColumns = [JoinColumn(name = "role_id")]
    )
    @OneToMany(fetch = FetchType.EAGER, orphanRemoval = true, cascade = [CascadeType.ALL])
    val roles: MutableList<Role>
) : UserCredentials, SoftDeleteEntity() {
    override fun getAuthorities(): MutableCollection<Authority> {
        return roles.map { it.name }
            .toMutableList()
    }

    override fun getUsername(): String {
        return username
    }

    override fun getPassword(): String {
        return password
    }

    override fun isEnabled(): Boolean {
        return !this.banned && !this.deleted
    }

    override fun isAccountNonLocked(): Boolean {
        return !this.banned
    }


}