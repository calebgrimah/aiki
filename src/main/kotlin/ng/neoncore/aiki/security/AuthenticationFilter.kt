package ng.neoncore.aiki.security

import ng.neoncore.aiki.service.JWTService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class AuthenticationFilter : OncePerRequestFilter() {

    @Autowired
    lateinit var jwtService: JWTService

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val jws = request.getHeader(HttpHeaders.AUTHORIZATION)
        if (jws != null) {
            val user = jwtService.getAuthUser(request)
            println("userdata23 ${user}")
            val authentication : Authentication = UsernamePasswordAuthenticationToken(user, null, emptyList())
            SecurityContextHolder.getContext().authentication = authentication
        }
        filterChain.doFilter(request,response)
    }
}