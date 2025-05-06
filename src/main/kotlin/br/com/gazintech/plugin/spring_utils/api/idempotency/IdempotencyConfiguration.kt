package br.com.gazintech.plugin.spring_utils.api.idempotency

import br.com.gazintech.plugin.spring_utils.utils.ReflectionUtils
import jakarta.annotation.PostConstruct
import org.slf4j.LoggerFactory
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.EnableAspectJAutoProxy
import org.springframework.context.annotation.Import
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories

@Configuration
@EnableAspectJAutoProxy
@EnableRedisRepositories
@ComponentScan
class IdempotencyConfiguration {
    companion object {
        @Suppress("JAVA_CLASS_ON_COMPANION")
        @JvmStatic
        private val logger = LoggerFactory.getLogger(javaClass.enclosingClass)
    }

    @PostConstruct
    fun init() {
        logger.trace("IdempotencyConfiguration initialized!")

        (ReflectionUtils.getAnnotationValue(
            IdempotencyConfiguration::class.java, Import::class.java, "value"
        ) as Array<*>?)?.forEach { value ->
            logger.trace("-> IdempotencyConfiguration importing: {}", value)
        }
    }
}