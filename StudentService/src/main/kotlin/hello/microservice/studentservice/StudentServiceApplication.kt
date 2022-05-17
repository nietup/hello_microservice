package hello.microservice.studentservice

import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig
import io.github.resilience4j.timelimiter.TimeLimiterConfig
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JCircuitBreakerFactory
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JConfigBuilder
import org.springframework.cloud.client.circuitbreaker.Customizer
import org.springframework.cloud.client.loadbalancer.LoadBalanced
import org.springframework.cloud.netflix.eureka.EnableEurekaClient
import org.springframework.context.annotation.Bean
import org.springframework.web.client.RestTemplate
import java.time.Duration

@SpringBootApplication
@EnableEurekaClient
class StudentServiceApplication {
    @Bean
    @LoadBalanced
    fun restTemplate() = RestTemplate()


    @Bean
    fun defaultCustomizer(): Customizer<Resilience4JCircuitBreakerFactory> =
        Customizer { factory ->
            factory.configureDefault { id ->
                Resilience4JConfigBuilder(id)
                    .timeLimiterConfig(
                        TimeLimiterConfig.custom().timeoutDuration(Duration.ofSeconds(6)).build()
                    )
                    .circuitBreakerConfig(CircuitBreakerConfig.ofDefaults())
                    .build()
            }
        }
}

fun main(args: Array<String>) {
    runApplication<StudentServiceApplication>(*args)
}
