package hello.microservice.studentservice

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.client.RestTemplate

@RestController
@RequestMapping("/students")
class StudentController(private val restTemplate: RestTemplate) {

    private val URI = "http://localhost:9001/grades/"

    @GetMapping("/{studentId}")
    fun getStudentWithGrade(@PathVariable studentId: Int): StudentGrade {

        val grade = restTemplate.getForObject(URI + studentId, Grade::class.java)

        return StudentGrade(Student("Sheran Aśtar", 1), grade!!)
    }
}