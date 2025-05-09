package br.com.gazintech.plugin.spring_utils

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component

/**
 * Created by IntelliJ IDEA.<br/>
 * User: luan-gazin<br/>
 * Date: 07/05/25<br/>
 * Time: 23:56<br/>
 * To change this template use File | Settings | File Templates.
 */
@Component
@ConfigurationProperties(prefix = "spring-utils")
class StringUtilsProperties {
    lateinit var idempotency: Idempotency

    class Idempotency {
        // Change from val to var to make it mutable
        var enabled: Boolean = false
        lateinit var cache: Cache

        class Cache {
            var bean: String = ""
            var expiration: Long = 0L
        }
    }
}


