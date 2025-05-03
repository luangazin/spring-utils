package br.com.gazintech.plugin.spring_utils

import br.com.gazintech.plugin.spring_utils.idempotency.IdempotencyConfiguration
import jakarta.annotation.PostConstruct
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.EnableAspectJAutoProxy
import org.springframework.context.annotation.Import
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories

@Configuration
@Import(IdempotencyConfiguration::class)
class SpringUtilsConfiguration {

    @PostConstruct
    fun init() {
        println("SpringUtilsConfiguration initialized!")
    }

}