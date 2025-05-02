package br.com.gazintech.plugin.spring_utils.idempotency

import br.com.gazintech.plugin.spring_utils.exception.IdempotencyKeyNotFoundException
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.reflect.MethodSignature
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.springframework.web.context.request.RequestContextHolder
import org.springframework.web.context.request.ServletRequestAttributes
import java.util.*

/**
 * * * Aspect that intercepts methods annotated with @Idempotent.
 * * It checks if the Idempotency-Key header is present in the request.
 * * If the key is present, it checks if the response is already cached in Redis.
 * * If the response is cached, it returns the cached response.
 * * If the response is not cached, it proceeds with the method execution and caches the response.
 *
 * * @param joinPoint The join point of the method execution.
 * * @return The response of the method execution.
 *
 * * @throws IdempotencyKeyNotFoundException If the Idempotency-Key header is not present in the request.
 * * @throws Exception If the method execution fails.
 *
 */
@Aspect
@Component
class IdempotencyAspect {
    companion object {
        @Suppress("JAVA_CLASS_ON_COMPANION")
        @JvmStatic
        private val logger = LoggerFactory.getLogger(javaClass.enclosingClass)
    }

    @Autowired
    private lateinit var repository: IdempotencyRepository // Inject the Redis template

    @Around("@annotation(Idempotent) && execution(* *(..))")
    fun handleIdempotency(joinPoint: ProceedingJoinPoint): Any {
        logger.trace("Idempotency aspect triggered")
        val methodSignature = joinPoint.signature as MethodSignature
        val idempotent = methodSignature.method.getAnnotation(Idempotent::class.java)!!
        val request = ((RequestContextHolder.getRequestAttributes() as ServletRequestAttributes).request)

        val idempotencyKey = UUID.fromString(
            request.getHeader("Idempotency-Key")
                ?: throw IdempotencyKeyNotFoundException("Idempotency-Key header is required")
        )
        logger.trace("Idempotency-Key: {}", idempotencyKey)
        val cacheItem = repository.getCache(idempotencyKey)
        if (cacheItem.isPresent) {
            logger.trace("Idempotency-Key found in cache: {}, return response", idempotencyKey)
            return cacheItem.get().response
        }

        logger.trace("Idempotency-Key not found in cache: {}, proceed with method execution", idempotencyKey)
        val result = joinPoint.proceed()

        repository.save(idempotencyKey, IdempotencyCache(idempotencyKey, result, idempotent.cacheTimeSeconds))

        logger.trace("Idempotency-Key saved in cache: {}.", idempotencyKey)
        return result
    }

}
