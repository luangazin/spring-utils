package br.com.gazintech.plugin.spring_utils.api.idempotency

import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.data.redis.core.RedisHash
import org.springframework.data.redis.core.TimeToLive
import java.io.Serializable
import java.util.*


@RedisHash(value = "idempotency-cache")
class IdempotencyCache(
    @JsonProperty(value = "idempotency-key", required = true) val idempotencyKey: UUID,
    @JsonProperty(value = "response", required = true) val response: Any,
    @TimeToLive val expirationInSeconds: Long
) : Serializable {
}