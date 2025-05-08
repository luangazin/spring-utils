package br.com.gazintech.plugin.spring_utils.api.idempotency.repository

import br.com.gazintech.plugin.spring_utils.api.idempotency.repository.IdempotencyCache
import java.util.*


interface IdempotencyRepository {
    fun getCache(idempotencyKey: UUID): Optional<IdempotencyCache>

    fun save(idempotencyKey: UUID, idempotency: IdempotencyCache)
}