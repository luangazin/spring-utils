package br.com.gazintech.plugin.spring_utils.idempotency

@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
annotation class Idempotent(val cacheTimeSeconds: Long = 60)
