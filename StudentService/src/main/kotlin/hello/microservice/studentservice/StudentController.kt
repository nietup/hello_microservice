package hello.microservice.studentservice

import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JCircuitBreakerFactory
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.client.RestTemplate

@RestController
@RequestMapping("/students")
class StudentController(private val restTemplate: RestTemplate,
                        private val circuitBreakerFactory: Resilience4JCircuitBreakerFactory) {

    companion object {
        private const val URI = "http://GRADE-SERVICE/grades/"
    }

    @GetMapping("/{studentId}")
    fun getStudentWithGrade(@PathVariable studentId: Int): StudentGrade {
        return circuitBreakerFactory.create("student").run( {
            val grade = restTemplate.getForObject(URI + studentId, Grade::class.java)

            StudentGrade(Student("Sheran Aśtar", 0), grade!!)
        },
            { StudentGrade(Student("fallback", 0), Grade(0.0, ""))})
    }
}