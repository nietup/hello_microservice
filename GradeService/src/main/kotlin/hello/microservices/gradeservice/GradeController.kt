package hello.microservices.gradeservice

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/grades")
class GradeController {
    @GetMapping("/{studentId}")
    fun getGrade(@PathVariable studentId: Int): Grade {
        return Grade(5.0*Math.random(), "Excellent")
    }
}