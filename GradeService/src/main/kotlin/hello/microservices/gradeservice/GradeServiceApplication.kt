package hello.microservices.gradeservice

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.netflix.eureka.EnableEurekaClient

@SpringBootApplication
@EnableEurekaClient
class GradeServiceApplication

fun main(args: Array<String>) {
	runApplication<GradeServiceApplication>(*args)
}
