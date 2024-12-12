package br.com.spotiparty.authentication

import br.com.spotiparty.extensions.toDate
import br.com.spotiparty.user.User
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.io.Decoders
import io.jsonwebtoken.security.Keys
import org.springframework.stereotype.Service
import java.time.OffsetDateTime
import java.time.ZoneOffset
import javax.crypto.SecretKey

@Service
class JwtService(
    private val jwtProperties: JwtProperties
) {
    fun generateToken(user: User): String {
        val utcNow = OffsetDateTime.now(ZoneOffset.UTC)
        return Jwts.builder()
            .claims()
            .issuer(jwtProperties.issuer)
            .subject(user.username)
            .issuedAt(utcNow.toDate())
            .expiration(utcNow.plusHours(1).toDate())
            .add("Mila", "Gostosa")
            .add("Cyan", "Esquizo")
            .add("Pelincha", "Betinha")
            .and()
            .signWith(getSignKey(), Jwts.SIG.HS256)
            .compact()
    }

    internal fun isTokenValid(token: String, user: User): Boolean {
        val username = getUsername(token)
        return username == user.username && !isTokenExpired(token)
    }

    internal fun getUsername(token: String): String? = extractClaim(token, Claims::getSubject)

    private fun getExpiration(token: String): OffsetDateTime = extractClaim(token, Claims::getExpiration).let {
        OffsetDateTime.ofInstant(it.toInstant(), ZoneOffset.UTC)
    }

    private fun isTokenExpired(token: String): Boolean {
        return getExpiration(token).isBefore(OffsetDateTime.now(ZoneOffset.UTC))
    }

    private fun <T> extractClaim(token: String, claimsResolver: (claim: Claims) -> T): T {
        return claimsResolver.invoke(extractAllClaims(token))
    }

    private fun extractAllClaims(token: String): Claims {
        return Jwts.parser()
            .verifyWith(getSignKey())
            .requireIssuer(jwtProperties.issuer)
            .build()
            .parseSignedClaims(token)
            .payload
    }

    private fun getSignKey(): SecretKey {
        val keyBytes = Decoders.BASE64.decode(jwtProperties.secret)
        return Keys.hmacShaKeyFor(keyBytes)
    }
}

