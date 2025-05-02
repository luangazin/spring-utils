package br.com.gazintech.plugin.spring_utils

import br.com.gazintech.plugin.spring_utils.idempotency.Idempotent
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.util.concurrent.atomic.AtomicLong

@JvmRecord
data class Greeting(val id: Long, val content: String?)

@RestController
class GreetingController {
    private val counter = AtomicLong()


    @GetMapping("/")
    @Idempotent(cacheTimeSeconds = 3600)
    fun greeting(@RequestParam(value = "name", defaultValue = "World") name: String?): Greeting? {
        return Greeting(counter.incrementAndGet(), String.format(template, name))
    }

    companion object {
        private const val template = "Hello, %s!"
    }
}