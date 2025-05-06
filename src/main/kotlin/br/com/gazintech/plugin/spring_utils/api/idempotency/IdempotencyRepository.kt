package br.com.gazintech.plugin.spring_utils.api.idempotency

import java.util.*


interface IdempotencyRepository {
    fun getCache(idempotencyKey: UUID): Optional<IdempotencyCache>

    fun save(idempotencyKey: UUID, idempotency: IdempotencyCache)
}