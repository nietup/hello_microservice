package hello.microservice.studentservice

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker
import mu.KotlinLogging
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.client.RestTemplate

@RestController
@RequestMapping("/students")
class StudentController(
    private val restTemplate: RestTemplate
) {

    companion object {
        private const val URI = "http://GRADE-SERVICE/grades/"
        private val logger = KotlinLogging.logger {}
    }

    @GetMapping("/{studentId}")
    @CircuitBreaker(name = "student")
    fun getStudentWithGrade(@PathVariable studentId: Int): StudentGrade {
        logger.info { "querying grade-service for studentId: $studentId" }
        val grade = restTemplate.getForObject(URI + studentId, Grade::class.java)
        return StudentGrade(Student("Sheran Aśtar", 0), grade!!)
    }
}