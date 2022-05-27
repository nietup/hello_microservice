package hello.microservice.studentservice

import mu.KotlinLogging
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JCircuitBreakerFactory
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.client.RestTemplate

@RestController
@RequestMapping("/students")
class StudentController(
    private val restTemplate: RestTemplate,
    private val circuitBreakerFactory: Resilience4JCircuitBreakerFactory
) {

    companion object {
        private const val URI = "http://GRADE-SERVICE/grades/"
    }

    private val logger = KotlinLogging.logger {}

    @GetMapping("/{studentId}")
    fun getStudentWithGrade(@PathVariable studentId: Int): StudentGrade = circuitBreakerFactory
        .create("student")
        .run(
            {
                logger.info { "querying grade-service for studentId: $studentId" }
                val grade = restTemplate.getForObject(URI + studentId, Grade::class.java)
                StudentGrade(Student("Sheran Aśtar", 0), grade!!)
            },
            {
                logger.error { "problem accessing grade-service" }
                StudentGrade(Student("fallback", 0), Grade(0.0, ""))
            }
        )
}