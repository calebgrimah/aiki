package ng.neoncore.aiki.service

import ng.neoncore.aiki.domain.AikiUser
import ng.neoncore.aiki.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.User.UserBuilder
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import java.util.*

@Service
class UserDetailsServiceImpl : UserDetailsService {
    @Autowired
    lateinit var userRepository : UserRepository

    override fun loadUserByUsername(username: String?): UserDetails {
        if(username.isNullOrEmpty())
            throw UsernameNotFoundException("Please provide username")
        val user : Optional<AikiUser> = userRepository.findUserByUsername(username)
        val builder : UserBuilder
        if(user.isPresent){
            val currentUser = user.get()
            builder = org.springframework.security.core.userdetails.User.withUsername(username)
            builder.password(currentUser.password)
            builder.roles(currentUser.role)
        } else {
            throw UsernameNotFoundException("User not found.")
        }
        return builder.build()
    }
}