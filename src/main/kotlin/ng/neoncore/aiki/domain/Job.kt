package ng.neoncore.aiki.domain

import java.time.LocalDateTime
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.OneToOne

@Entity
class Job(
    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    var id : Long? = null,
    var title : String,
    var description : String,
    var category : String,//remote,onsite
    var type : String,//contract, fulltime
    var createdAt : LocalDateTime = LocalDateTime.now(),
    var expireAt : LocalDateTime,
    @OneToOne
    var company: Company
)
