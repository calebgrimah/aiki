package ng.neoncore.aiki.domain

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
class AikiUser
    (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    var id: Long? = null,
    @Column(nullable = false, unique = true)
    var username: String = "",
    var firstname: String = "",
    var lastname: String = "",
    var password: String = "",
    var email: String = "",
    var role: String = ""
) {
}