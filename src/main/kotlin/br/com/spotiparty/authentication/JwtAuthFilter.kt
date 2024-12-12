package br.com.spotiparty.authentication

import br.com.spotiparty.login.UserSignOnUseCase
import br.com.spotiparty.user.LoggedUser
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.HttpHeaders
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

@Component
internal class JwtAuthFilter(
    private val jwtService: JwtService,
    private val userSignOnUseCase: UserSignOnUseCase
) : OncePerRequestFilter() {
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val authorizationHeader: String? = request.getHeader(HttpHeaders.AUTHORIZATION)

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            val token = authorizationHeader.substring(7)
            val username = jwtService.getUsername(token)
            if (username != null) {
                val user = userSignOnUseCase.loadUserByUsername(username)
                LoggedUser.USER_ID.set(user.id)
                if (jwtService.isTokenValid(token, user))
                    SecurityContextHolder.getContext().authentication = UsernamePasswordAuthenticationToken(
                        user,
                        null,
                        user.authorities
                    ).apply {
                        details = WebAuthenticationDetailsSource().buildDetails(request)
                    }
            }
        }

        LoggedUser.SEE_DELETED.set(false)

        filterChain.doFilter(request, response)
    }
}