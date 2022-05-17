package ng.neoncore.aiki.controller

import ng.neoncore.aiki.domain.AccountCredentials
import ng.neoncore.aiki.domain.AikiUser
import ng.neoncore.aiki.repository.UserRepository
import ng.neoncore.aiki.service.JWTService
import org.apache.catalina.User
import org.apache.coyote.http11.Constants.a
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class AuthController {

    @Autowired
    lateinit var jwtService: JWTService

    @Autowired
    lateinit var authenticationManager: AuthenticationManager

    @Autowired
    lateinit var userRepository: UserRepository

    @Autowired
    lateinit var passwordEncoder : PasswordEncoder


    @PostMapping(value = ["/login"])
    fun getToken(@RequestBody accountCredentials: AccountCredentials) : ResponseEntity<Any>{
        val credentials = UsernamePasswordAuthenticationToken(accountCredentials.username, accountCredentials.password)
        val auth: Authentication = authenticationManager.authenticate(credentials)
        val jwts = jwtService.getToken(auth.name)
        return ResponseEntity.ok().header(HttpHeaders.AUTHORIZATION, "Bearer $jwts")
            .header(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, "Authorization").build()
    }

    @PostMapping(value = ["/signup"])
    fun signup (@RequestBody user : AikiUser) : ResponseEntity<Any>{
        if(userRepository.findUserByUsername(user.username).isPresent){
            return ResponseEntity.badRequest().build()
        }
        val aikiUser = AikiUser(
            username = user.username,
            password = passwordEncoder.encode(user.password),
            firstname = user.firstname,
            lastname = user.lastname,
            email = user.email,
            role = user.role
        )
        val savedUser = userRepository.save(aikiUser)
        return ResponseEntity(savedUser, HttpStatus.CREATED)
    }
}