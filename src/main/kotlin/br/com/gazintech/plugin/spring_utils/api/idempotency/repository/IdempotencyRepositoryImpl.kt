package br.com.gazintech.plugin.spring_utils.api.idempotency.repository

import br.com.gazintech.plugin.spring_utils.api.idempotency.repository.IdempotencyCache
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.redis.core.RedisKeyValueTemplate
import org.springframework.stereotype.Component
import java.util.*

@Component
class IdempotencyRepositoryImpl : IdempotencyRepository {
    companion object {
        @Suppress("JAVA_CLASS_ON_COMPANION")
        @JvmStatic
        private val logger = LoggerFactory.getLogger(javaClass.enclosingClass)
    }

    @Autowired
    private lateinit var redisTemplate: RedisKeyValueTemplate

    /**
     * * Fetches the idempotency key from Redis.
     */
    override fun getCache(idempotencyKey: UUID): Optional<IdempotencyCache> {
        val redisKey = getRedisKey(idempotencyKey)
        logger.debug("Fetching idempotency key from Redis: $redisKey")
        return redisTemplate.findById<IdempotencyCache>(redisKey, IdempotencyCache::class.java)
    }

    /**
     * * Saves the idempotency key in Redis.
     */
    override fun save(
        idempotencyKey: UUID,
        idempotency: IdempotencyCache
    ) {
        val redisKey = getRedisKey(idempotencyKey)
        logger.debug("Creating idempotency key in Redis: $redisKey with TTL: ${idempotency.expirationInSeconds}")
        redisTemplate.update<IdempotencyCache>(redisKey, idempotency)

    }

    /**
     * * Generates the Redis key for the idempotency key.
     */
    private fun getRedisKey(idempotencyKey: UUID): String {
        return "idempotency:${idempotencyKey.toString()}"
    }
}