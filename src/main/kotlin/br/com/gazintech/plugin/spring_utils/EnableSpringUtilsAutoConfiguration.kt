package br.com.gazintech.plugin.spring_utils

import br.com.gazintech.plugin.spring_utils.idempotency.IdempotencyConfiguration
import org.springframework.boot.autoconfigure.ImportAutoConfiguration
import org.springframework.context.annotation.Configuration

@Configuration
@ImportAutoConfiguration(classes = [IdempotencyConfiguration::class])
@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
annotation class EnableSpringUtilsAutoConfiguration()
