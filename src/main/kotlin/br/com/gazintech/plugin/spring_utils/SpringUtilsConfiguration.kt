package br.com.gazintech.plugin.spring_utils

import br.com.gazintech.plugin.spring_utils.api.idempotency.IdempotencyConfiguration
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import

@Configuration
@Import(IdempotencyConfiguration::class, RedisConfiguration::class)
@EnableConfigurationProperties(SpringUtilsProperties::class)
class SpringUtilsConfiguration {


}