package ng.neoncore.aiki.domain

import java.time.LocalDateTime
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.ManyToOne

@Entity
class Company(
    @Id
    @GeneratedValue
    var id: Long? = null,
    var companyName: String,
    var companyAddress: String
)