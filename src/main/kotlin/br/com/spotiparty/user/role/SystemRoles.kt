package br.com.spotiparty.user.role

enum class SystemRoles(private val roleName: String) : Authority {
    SYSTEM_ACCESS("SYSTEM_ACCESS");

    override fun getAuthority(): String {
        return roleName
    }
}
