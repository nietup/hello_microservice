package hello.microservices.gradeservice

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import kotlin.math.abs

@RestController
@RequestMapping("/grades")
class GradeController {
    private var sleepTime = 0L

    @GetMapping("/{studentId}")
    fun getGrade(@PathVariable studentId: Int): Grade {
        Thread.sleep(sleepTime)
        return Grade(5.0*Math.random(), "Excellent")
    }

    @GetMapping("/switch")
    fun switchSlowdown(): String {
        sleepTime = abs(sleepTime - 3000L)

        return "sleep time updated to $sleepTime"
    }
}