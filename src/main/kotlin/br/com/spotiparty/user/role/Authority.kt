package br.com.spotiparty.user.role

import jakarta.persistence.AttributeConverter
import org.slf4j.LoggerFactory
import org.springframework.security.core.GrantedAuthority

interface Authority : GrantedAuthority {
    override fun getAuthority(): String
}

class DbAuthority(
    private val role: String
) : Authority {
    override fun getAuthority(): String {
        return role
    }
}

class AuthorityConverter : AttributeConverter<Authority, String> {
    override fun convertToDatabaseColumn(attribute: Authority?): String? {
        return attribute?.authority
    }

    override fun convertToEntityAttribute(dbData: String?): Authority? {
        val logger = LoggerFactory.getLogger(this.javaClass)
        if (dbData.isNullOrBlank()) {
            logger.warn("Converted Role has no name.")
            return null
        }
        return DbAuthority(dbData)
    }

}