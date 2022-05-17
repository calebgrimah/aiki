package ng.neoncore.aiki

import ng.neoncore.aiki.domain.AikiUser
import ng.neoncore.aiki.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class AikiApplication

fun main(args: Array<String>) {
    runApplication<AikiApplication>(*args)
}