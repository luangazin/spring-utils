package br.com.gazintech.plugin.spring_utils.api.idempotency

import org.springframework.context.annotation.Import

/**
 * Created by IntelliJ IDEA.<br/>
 * User: luan-gazin<br/>
 * Date: 03/05/25<br/>
 * Time: 01:44<br/>
 * To change this template use File | Settings | File Templates.
 */
@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
@Import(IdempotencyConfiguration::class)
annotation class EnableIdempotencyAutoConfiguration()
