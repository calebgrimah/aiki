package ng.neoncore.aiki.repository

import ng.neoncore.aiki.domain.Job
import org.springframework.data.repository.CrudRepository

interface JobApplicationRepository : CrudRepository<Job, Long>{
    fun findJobsByTitleOrderByCreatedAtDesc(title : String): List<Job>
}