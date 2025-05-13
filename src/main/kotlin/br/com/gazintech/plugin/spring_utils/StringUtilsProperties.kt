package br.com.gazintech.plugin.spring_utils

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.NestedConfigurationProperty
import org.springframework.boot.context.properties.bind.DefaultValue

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
    val cache: CacheProperties,

    @NestedConfigurationProperty
    val idempotency: IdempotencyProperties
)

data class CacheProperties(
    @DefaultValue("false")
    val enabled: Boolean,

    @DefaultValue("redis")
    val type: String,

    @NestedConfigurationProperty
    val redis: RedisProperties
)

data class RedisProperties(
    @DefaultValue("localhost")
    val host: String,

    @DefaultValue("6379")
    val port: Int,

    @DefaultValue("")
    val password: String,

    @DefaultValue("0")
    val index: Int,

    @NestedConfigurationProperty
    val poll: RedisPoolProperties
)

data class RedisPoolProperties(
    @DefaultValue("1")
    val minIdle: Int,

    @DefaultValue("10")
    val maxIdle: Int,

    @DefaultValue("20")
    val maxTotal: Int,

    @DefaultValue("2000")
    val maxWait: Long,

    @DefaultValue("3000")
    val commandTimeout: Long
)

data class IdempotencyProperties(
    @DefaultValue("true")
    val enabled: Boolean,

    @NestedConfigurationProperty
    val cache: IdempotencyCacheProperties
)

data class IdempotencyCacheProperties(
    @DefaultValue("60000")
    val expiration: Long
)


