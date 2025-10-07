package br.com.gazintech.plugin.spring_utils

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.NestedConfigurationProperty

/**
 * Created by IntelliJ IDEA.<br/>
 * User: luan-gazin<br/>
 * Date: 07/05/25<br/>
 * Time: 23:56<br/>
 * To change this template use File | Settings | File Templates.
 */
@ConfigurationProperties(prefix = "spring-utils")
data class SpringUtilsProperties(
    @NestedConfigurationProperty
    val cache: CacheProperties = CacheProperties(),

    @NestedConfigurationProperty
    val idempotency: IdempotencyProperties = IdempotencyProperties()
){}

data class CacheProperties(
    val enabled: Boolean = false,
    val type: String = "redis",
    @NestedConfigurationProperty
    val redis: RedisProperties = RedisProperties()
)

data class RedisProperties(
    val host: String = "localhost",
    val port: Int = 6379,
    val password: String = "",
    val index: Int = 0,
    @NestedConfigurationProperty
    val poll: RedisPoolProperties = RedisPoolProperties()
)

data class RedisPoolProperties(
    val minIdle: Int = 1,
    val maxIdle: Int = 10,
    val maxTotal: Int = 20,
    val maxWait: Long = 2000,
    val commandTimeout: Long = 3000
)

data class IdempotencyProperties(
    val enabled: Boolean = true,
    @NestedConfigurationProperty
    val cache: IdempotencyCacheProperties = IdempotencyCacheProperties()
)

data class IdempotencyCacheProperties(
    val expiration: Long = 60000
)


