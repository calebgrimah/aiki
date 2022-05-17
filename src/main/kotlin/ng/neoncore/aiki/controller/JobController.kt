package ng.neoncore.aiki.controller

import ng.neoncore.aiki.domain.Job
import ng.neoncore.aiki.domain.JobApplication
import ng.neoncore.aiki.domain.JobApplicationRequest
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(value = ["/jobs"])
class JobController {

    @PostMapping
    fun createJob(@RequestBody job : Job){}
    @DeleteMapping(value = ["/{jobId}"])
    fun deleteJob(@PathVariable jobId : Long){}
    @GetMapping
    fun getJobs() : List<String>{
        return listOf("1","2","3")
    }
    @GetMapping(value = ["/{jobId}"])
    fun getSingleJob(@PathVariable jobId : Long){}
    @GetMapping("/{title}")
    fun searchJobs(@PathVariable title : String){}
    @PostMapping(value = ["/apply"])
    fun applyToJob(@RequestBody jobApplication: JobApplicationRequest){}
}