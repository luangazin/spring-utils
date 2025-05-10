package br.com.gazintech.plugin.spring_utils

import org.apache.commons.pool2.impl.GenericObjectPoolConfig
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.connection.RedisConnectionFactory
import org.springframework.data.redis.connection.RedisPassword
import org.springframework.data.redis.connection.RedisStandaloneConfiguration
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory
import org.springframework.data.redis.connection.lettuce.LettucePoolingClientConfiguration
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer
import org.springframework.data.redis.serializer.StringRedisSerializer
import java.time.Duration


@Configuration
class RedisConfiguration {

    @Autowired
    private lateinit var properties: SpringUtilsProperties


    @Bean
    @ConditionalOnMissingBean(RedisConnectionFactory::class)
    fun lettuceConnectionFactory(): RedisConnectionFactory {
        val config = RedisStandaloneConfiguration()
        config.hostName = properties.cache.redis.host
        config.port = properties.cache.redis.port
        if(properties.cache.redis.password.isNotEmpty()) {
            config.password = RedisPassword.of(properties.cache.redis.password)
        }
        config.setDatabase(properties.cache.redis.index) // Redis database index

        val poolConfig: GenericObjectPoolConfig<Any> = GenericObjectPoolConfig<Any>()
        poolConfig.setMaxWait(Duration.ofMillis(3000)) // Maximum wait time for obtaining a connection
        poolConfig.maxIdle = properties.cache.redis.poll.maxIdle
        poolConfig.minIdle = properties.cache.redis.poll.minIdle
        poolConfig.maxTotal = properties.cache.redis.poll.maxTotal
        poolConfig.setMaxWait(Duration.ofMillis(properties.cache.redis.poll.maxWait))  // Maximum wait time for obtaining a connection

        val poolingClientConfig =
            LettucePoolingClientConfiguration.builder()
                .commandTimeout(Duration.ofMillis(3000)) // Command timeout
                .poolConfig(poolConfig) // Set connection pool configuration
                .build()

        return LettuceConnectionFactory(config, poolingClientConfig)
    }

    @Bean
    @ConditionalOnMissingBean(RedisTemplate::class)
    fun redisTemplate(redisConnectionDetailsForRedis: RedisConnectionFactory): RedisTemplate<String, Any> {
        return RedisTemplate<String, Any>().apply {
            connectionFactory = redisConnectionDetailsForRedis
            keySerializer = StringRedisSerializer()
            valueSerializer = GenericJackson2JsonRedisSerializer()
            hashKeySerializer = StringRedisSerializer()
            hashValueSerializer = GenericJackson2JsonRedisSerializer()
        }
    }
}