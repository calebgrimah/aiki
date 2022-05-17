package ng.neoncore.aiki.service

import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.security.Keys
import org.springframework.http.HttpHeaders
import org.springframework.stereotype.Service
import java.security.Key
import java.util.*
import javax.servlet.http.HttpServletRequest

@Service
class JWTService {
    companion object {
        const val  EXPIRATION_TIME = 86400000
        const val  PREFIX = "Bearer"
        val key : Key = Keys.secretKeyFor(SignatureAlgorithm.HS256)

    }
    fun getAuthUser(request : HttpServletRequest) : String? {
        val token = request.getHeader(HttpHeaders.AUTHORIZATION)
        if(token != null){
            val user = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token.replace(PREFIX, "")).body.subject
            if (user!= null){
                return user;
            }
            return null
        }
        return null;
    }


    fun getToken(username : String) : String{
        val token = Jwts.builder().setSubject(username).setExpiration(Date(System.currentTimeMillis() + EXPIRATION_TIME)).signWith(
            key).compact()
        return token
    }
}