package hello.microservices.gradeservice

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class GradeServiceApplication

fun main(args: Array<String>) {
	runApplication<GradeServiceApplication>(*args)
}
