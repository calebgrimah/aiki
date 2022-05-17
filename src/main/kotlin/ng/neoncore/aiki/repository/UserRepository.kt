package ng.neoncore.aiki.repository

import ng.neoncore.aiki.domain.AikiUser
import org.springframework.data.repository.CrudRepository
import java.util.Optional

interface UserRepository  : CrudRepository<AikiUser, Long> {
    fun findUserByUsername(username : String) : Optional<AikiUser>
}