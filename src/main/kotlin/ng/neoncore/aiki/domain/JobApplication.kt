package ng.neoncore.aiki.domain

import java.time.LocalDateTime
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.ManyToOne
import javax.persistence.OneToOne

@Entity
class JobApplication (
    @Id
    @GeneratedValue
    var id: Long? = null,
    var jobId: Long,
    var userId: Long,
    var applicationDate: LocalDateTime = LocalDateTime.now()
 )